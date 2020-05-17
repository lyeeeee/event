package com.rcd.iotsubsys.service.deduce;

import com.alibaba.fastjson.JSONObject;
import com.microsoft.z3.*;

import com.rcd.iotsubsys.domain.event.EventConstraint;
import com.rcd.iotsubsys.domain.event.MetaEventAttrRelation;
import com.rcd.iotsubsys.domain.event.PubSubEvent;
import com.rcd.iotsubsys.domain.event.Valid;
import com.rcd.iotsubsys.domain.knowledge.KnowledgeComplexEvent;
import com.rcd.iotsubsys.domain.knowledge.KnowledgeComplexSubEvnet;
import com.rcd.iotsubsys.domain.knowledge.KnowledgeComplexTarget;
import com.rcd.iotsubsys.domain.knowledge.KnowledgeMetaEvent;
import com.rcd.iotsubsys.dto.response.JsonResult;
import com.rcd.iotsubsys.service.event.ComplexEventService;
import com.rcd.iotsubsys.service.event.MetaEventService;
import com.rcd.iotsubsys.service.util.StringUtil;
import com.rcd.iotsubsys.util.ClassUtil;
import com.rcd.iotsubsys.util.PublishUtil;
import com.rcd.iotsubsys.util.SubscribeUtil;

import com.rcd.iotsubsys.wsn.subscribe.soap.wsn.UserNotificationProcessImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @program: iot-knowledge-sub-system
 * @description: ${description}
 * @author: liyi
 * @create: 2020-04-28 10:23
 */
public class Deducer implements Runnable {
    /**
     * 所有的deducer只订阅一次发布订阅消息，订阅telemetry主题，所有消息都统一处理
     * */
    private static volatile boolean subscribed = false;

    private static final Logger LOGGER = LoggerFactory.getLogger(Deducer.class);
    /**
     * 默认时间窗口两分钟
     * */
    private static final long DEFAULT_TIME_WINDOW = 2*60*1000;

    /**
     * 默认长度窗口100
     * */
    private static final int DEFAULT_LENGTH_WINDOW =  100;

    private static Map<String, Integer> deviceIdMap;

    private Context context = new Context();

    private Solver solver = context.mkSolver();

    public Long complexId;

    public Map<String, Class<?>> cachedTopicClasses = new HashMap<>();

    public volatile boolean stop;

    public UserNotificationProcessImpl userNotificationProcessImpl;

    private DeduceContext deduceContext;

    private ComplexEventService complexEventService;

    private MetaEventService metaEventService;

    public Map<String, Deque<PubSubEvent>> eventBuffer = new HashMap<>();

    /**
     * 记录首尾事件，避免重复推理
     * */
    PubSubEvent begin, end;

    private boolean useTimeWindow = true;

    private boolean useDefaultWindowPolicy = false;

    private long timeWindow;

    private long lenWindow;

    /**
     * String msg = "<data_table>" + "39.0" + "</data_table>" +
     *             "<site_name>" + "11" + "</site_name>" +
     *             "<device_name>" + new Date().toString() +"</device_name>" +
     *             "<data_name>" + "data_name" + "</data_name>" +
     *             "<timestamp>" + "timestamp" + "</timestamp>" +
     *             "<detected_value>" + "value" + "</detected_value>";
     *         Matcher matcher = pattern.matcher(msg);
     *         while (matcher.find()) {
     *             System.out.println(matcher.group(1) + " " + matcher.group(2));
     *         }
     * */
    private static Pattern pattern = Pattern.compile("<(.*?)>([0-9a-zA-Z\\._:\\s]+)</.*?>");

    /**
     * 发布订阅线程生产， 当前线程消费
     * */
    public BlockingQueue<String> messageQueue = new LinkedBlockingQueue<>();

    static {
        deviceIdMap = new HashMap<>();
    }

    public Deducer(Long complexId, UserNotificationProcessImpl userNotificationProcessImpl
    ,ComplexEventService complexEventService, MetaEventService metaEventService, DeduceContext deduceContext) {
        this.complexId = complexId;
        this.userNotificationProcessImpl = userNotificationProcessImpl;
        this.complexEventService = complexEventService;
        this.metaEventService = metaEventService;
        this.deduceContext = deduceContext;
        this.userNotificationProcessImpl.registDeducer(this, SubscribeUtil.TOPIC_TELEMTRY);
    }

    @Override
    public void run() {
        try {
            deduce(complexId);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void deduce(Long complexId) throws InterruptedException {
        KnowledgeComplexEvent complexEvent = (KnowledgeComplexEvent) complexEventService.getComplexEventById(complexId).getData();

        JsonResult<Object> result = complexEventService.getAllSubEvent(complexId);
        // 复杂事件包括了哪些subEvent
        List<KnowledgeComplexSubEvnet> allSubEvents = (List<KnowledgeComplexSubEvnet>) result.getData();

        // 复杂事件包括了哪些目标
        List<KnowledgeComplexTarget> allTargets = (List<KnowledgeComplexTarget>) complexEventService.getAllTarget(complexId).getData();
        // 设置时间窗口或长度窗口
        if (allTargets.get(0).getTimeWindow() == null && allTargets.get(0).getLenWindow() == null) {
            useDefaultWindowPolicy = true;
        } else {
            if (allTargets.get(0).getTimeWindow() != null) {
                useTimeWindow = true;
                timeWindow = Integer.parseInt(allTargets.get(0).getTimeWindow()) * 60 * 1000;
            } else if(allTargets.get(0).getLenWindow() == null){
                useDefaultWindowPolicy = true;
            } else {
                useTimeWindow = false;
                lenWindow = allTargets.get(0).getLenWindow();
            }
        }
        List<Long> metaEventIds = allSubEvents.stream().map(KnowledgeComplexSubEvnet::getSubeventId).collect(Collectors.toList());
        // 所有的metaEvent
        List<KnowledgeMetaEvent> allMetaEvents = (List<KnowledgeMetaEvent>) metaEventService.getMetaEventByIds(metaEventIds).getData();
        // key: metaEvnetId   value: metaEvent
        Map<Long, KnowledgeMetaEvent> eventIdWithMetaEvent = allMetaEvents.stream().collect(Collectors.toMap(KnowledgeMetaEvent::getId, Function.identity()));
        // 这些原子事件涉及了哪些需要推理的属性
        List<MetaEventAttrRelation> allAttrRelation =
            (List<MetaEventAttrRelation>) metaEventService.getAllRelationByIds(metaEventIds).getData();
        // 消息同知识uri的映射
        Map<String, String> messageMapToKnowledgeUri = new HashMap<>();
        // 原子事件id+属性名同uri的映射
        Map<String, String> metaEventIdAndAttrMapToUri = new HashMap<>();
        for (MetaEventAttrRelation relation : allAttrRelation) {
            messageMapToKnowledgeUri.put(relation.getDeviceName()+relation.getDataName(), relation.getKnowledgeAttribute());
            metaEventIdAndAttrMapToUri.put(relation.getMetaEventId()+relation.getTopicAttribute(), relation.getKnowledgeAttribute());
        }

        Map<Long, List<KnowledgeComplexSubEvnet>> metaEventIdWithSubEvent = new HashMap<>();
        Map<Long, List<KnowledgeComplexTarget>> metaEventIdWithTarget = new HashMap<>();
        for (KnowledgeComplexSubEvnet subEvnet : allSubEvents) {
            List<KnowledgeComplexSubEvnet> tmp;
            if ((tmp = metaEventIdWithSubEvent.get(subEvnet.getSubeventId())) == null) {
                tmp = new ArrayList<>();
            }
            tmp.add(subEvnet);
            metaEventIdWithSubEvent.put(subEvnet.getSubeventId(), tmp);
        }
        for (KnowledgeComplexTarget target : allTargets) {
            List<KnowledgeComplexTarget> tmp;
            if ((tmp = metaEventIdWithTarget.get(target.getSubeventId())) == null) {
                tmp = new ArrayList<>();
            }
            tmp.add(target);
            metaEventIdWithTarget.put(target.getSubeventId(), tmp);
        }
        // 每一个属性映射即是推理的一个事件，针对每一条属性映射，找到对于该属性映射的限制，
        EventConstraint eventConstraint = new EventConstraint();

        // 设置整体子事件属性限制
        eventConstraint.setLogicRelation(complexEvent.getIdRelation());

        for (MetaEventAttrRelation metaEventAttrRelation : allAttrRelation) {

            eventConstraint.addDeviceNameAndDataName(metaEventAttrRelation.getDeviceName(), metaEventAttrRelation.getDataName());

            eventConstraint.addMetaEventAttrRelation(metaEventAttrRelation);

            // 一条属性映射，对应的所有的子事件限制
            List<KnowledgeComplexSubEvnet> tmp = metaEventIdWithSubEvent.get(metaEventAttrRelation.getMetaEventId());
            for (KnowledgeComplexSubEvnet subEvnet : tmp) {
                String relationValue = subEvnet.getRelationValue();
                Valid valid;
                if (relationValue.contains("~")) {
                    valid = new Valid(subEvnet.getId()
                        , subEvnet.getAttributeRelation()
                        , Double.parseDouble(relationValue.split("~")[0])
                        , Double.parseDouble(relationValue.split("~")[1])
                        , subEvnet.getSubeventId()
                        , metaEventAttrRelation.getTopicAttribute());
                } else {
                    valid = new Valid(subEvnet.getId()
                        , subEvnet.getAttributeRelation()
                        , Double.parseDouble(subEvnet.getRelationValue())
                        , subEvnet.getSubeventId()
                        , metaEventAttrRelation.getTopicAttribute());
                }
                // valid的id是子事件限制id
                eventConstraint.addValid(valid);
            }

        }

        // 整体目标限制
        EventConstraint targetConstraint = new EventConstraint();
        targetConstraint.setLogicRelation(complexEvent.getIdTargetRelation());
        for (MetaEventAttrRelation metaEventAttrRelation : allAttrRelation) {
            // 一条属性映射，对应的所有的子事件限制
            List<KnowledgeComplexTarget> tmp = metaEventIdWithTarget.get(metaEventAttrRelation.getMetaEventId());
            for (KnowledgeComplexTarget target : tmp) {
                // valid的id是子事件限制id
                String relationValue = target.getRelationValue();
                Valid valid;
                if (relationValue.contains("~")) {
                    valid = new Valid(target.getId()
                        , target.getAttributeRelation()
                        , Double.parseDouble(relationValue.split("~")[0])
                        , Double.parseDouble(relationValue.split("~")[1])
                        , target.getSubeventId()
                        , metaEventAttrRelation.getTopicAttribute());
                } else {
                    valid = new Valid(target.getId()
                        , target.getAttributeRelation()
                        , Double.parseDouble(target.getRelationValue())
                        , target.getSubeventId()
                        , metaEventAttrRelation.getTopicAttribute());
                }
                // valid的id是子事件限制id
                targetConstraint.addValid(valid);
            }
        }

        for (MetaEventAttrRelation relation : allAttrRelation) {
            String queueName = relation.getDeviceName()+ relation.getDataName();
            eventBuffer.put(queueName, new LinkedBlockingDeque<>());
        }

        Map<String, FuncDecl> uriToFunction = new HashMap<>();

        String targetRelation = '(' + complexEvent.getIdTargetRelation() + ')';

        Map<Long, BoolExpr> quantifierMap = new HashMap<>();

        FuncDecl[] funcDecls = new FuncDecl[allAttrRelation.size()];

        Sort longSort = context.mkIntSort();
        Sort stringSort = context.mkStringSort();
        Sort doubleSort = context.mkFPSortDouble();

        Expr[][] constVariable = new Expr[allAttrRelation.size()][2];
        for (int i = 0;i < allAttrRelation.size(); ++i) {
            MetaEventAttrRelation relation = allAttrRelation.get(i);
            String uri = relation.getKnowledgeAttribute();
            // 构造函数和变量
            funcDecls[i] = context.mkFuncDecl(uri, new Sort[]{stringSort, longSort}, doubleSort);

            uriToFunction.put(relation.getKnowledgeAttribute(), funcDecls[i]);
            constVariable[i][0] = context.mkConst("x" + i, stringSort);
            constVariable[i][1] = context.mkConst("y" + i, longSort);

            // 找到该属性涉及到的目标约束
            List<Valid> valids = targetConstraint.validsMap.get(relation.getMetaEventId()+relation.getTopicAttribute());
            for (Valid valid : valids) {
                Quantifier quantifier = null;
                if (valid.getComparator().equals("0")) {
                    quantifier = context.mkForall(
                        new Expr[]{constVariable[i][0], constVariable[i][1]}
                        , context.mkFPLt((FPExpr)context.mkApp(funcDecls[i], constVariable[i][0], constVariable[i][1])
                            , context.mkFP(valid.getValue(), context.mkFPSortDouble())), 1, null, null, null, null);
                } else if (valid.getComparator().equals("1")) {
                    quantifier = context.mkForall(
                        new Expr[]{constVariable[i][0], constVariable[i][1]}
                        , context.mkFPLEq((FPExpr)context.mkApp(funcDecls[i], constVariable[i][0], constVariable[i][1])
                            , context.mkFP(valid.getValue(), context.mkFPSortDouble())), 1, null, null, null, null);
                } else if (valid.getComparator().equals("2")) {
                    quantifier = context.mkForall(
                        new Expr[]{constVariable[i][0], constVariable[i][1]}
                        , context.mkFPEq((FPExpr)context.mkApp(funcDecls[i], constVariable[i][0], constVariable[i][1])
                            , context.mkFP(valid.getValue(), context.mkFPSortDouble())), 1, null, null, null, null);
                } else if (valid.getComparator().equals("3")) {
                    quantifier = context.mkForall(
                        new Expr[]{constVariable[i][0], constVariable[i][1]}
                        , context.mkFPGEq((FPExpr)context.mkApp(funcDecls[i], constVariable[i][0], constVariable[i][1])
                            , context.mkFP(valid.getValue(), context.mkFPSortDouble())), 1, null, null, null, null);
                } else if (valid.getComparator().equals("4")) {
                    Expr left = context.mkApp(funcDecls[i], constVariable[i][0], constVariable[i][1]);
                    FPExpr right = context.mkFP(valid.getValue(), context.mkFPSortDouble());
                    Expr tmp = context.mkFPGt((FPExpr) left, right);
                    quantifier = context.mkForall(new Expr[]{constVariable[i][0], constVariable[i][1]}, tmp, 1, null, null, null, null);
                } else if (valid.getComparator().equals("5")) {
                    BoolExpr expr1 = context.mkFPGt((FPExpr)context.mkApp(funcDecls[i], constVariable[i][0], constVariable[i][1]), context.mkFP(valid.getValue(), context.mkFPSortDouble()));
                    BoolExpr expr2 = context.mkFPLt((FPExpr)context.mkApp(funcDecls[i], constVariable[i][0], constVariable[i][1]), context.mkFP(valid.getAnotherValue(), context.mkFPSortDouble()));

                    quantifier = context.mkForall(
                        new Expr[]{constVariable[i][0], constVariable[i][1]}
                        ,context.mkAnd(expr1, expr2)
                            , 1, null, null, null, null);
                } else if (valid.getComparator().equals("6")) {
                    quantifier = context.mkForall(
                        new Expr[]{constVariable[i][0], constVariable[i][1]}
                        , context.mkNot(context.mkFPEq((FPExpr)context.mkApp(funcDecls[i], constVariable[i][0], constVariable[i][1])
                            , context.mkFP(valid.getValue(), context.mkFPSortDouble()))), 1, null, null, null, null);
                } else if (valid.getComparator().equals("7")) {
                    BoolExpr expr1 = context.mkFPGEq((FPExpr)context.mkApp(funcDecls[i], constVariable[i][0], constVariable[i][1]), context.mkFP(valid.getValue(), context.mkFPSortDouble()));
                    BoolExpr expr2 = context.mkFPLEq((FPExpr)context.mkApp(funcDecls[i], constVariable[i][0], constVariable[i][1]), context.mkFP(valid.getAnotherValue(), context.mkFPSortDouble()));

                    quantifier = context.mkForall(
                        new Expr[]{constVariable[i][0], constVariable[i][1]}
                        ,context.mkAnd(expr1, expr2)
                        , 1, null, null, null, null);
                }
                quantifierMap.put(valid.getId(), quantifier);
            }
        }
        char[] arr = targetRelation.toCharArray();
        Stack<Character> cStack = new Stack<>();
        Stack<BoolExpr> exprStack = new Stack<>();
        for (char c : arr) {
            if (c == ')') {
                String tmp = "";
                while (cStack.peek() != '(') {
                    tmp = cStack.pop() + tmp;
                }
                cStack.pop();
                if (tmp.contains("&")) {
                    Long left = Long.parseLong(tmp.split("&")[0]);
                    Long right = Long.parseLong(tmp.split("&")[0]);
                    BoolExpr boolExpr = context.mkAnd(exprStack.pop(), exprStack.pop());
                    String newNum = left + "" + right;
                    quantifierMap.put(Long.parseLong(newNum), boolExpr);
                    exprStack.push(boolExpr);
                    for (char i : newNum.toCharArray()) {
                        cStack.push(i);
                    }
                } else if(tmp.contains("|")) {
                    Long left = Long.parseLong(tmp.split("\\|")[0]);
                    Long right = Long.parseLong(tmp.split("\\|")[0]);
                    BoolExpr boolExpr = context.mkOr(exprStack.pop(), exprStack.pop());
                    String newNum = left + "" + right;
                    quantifierMap.put(Long.parseLong(newNum), boolExpr);
                    exprStack.push(boolExpr);
                    for (char i : newNum.toCharArray()) {
                        cStack.push(i);
                    }
                } else {
                    for (char i : tmp.toCharArray()) {
                        cStack.push(i);
                    }
                    exprStack.push(quantifierMap.get(Long.parseLong(tmp)));
                }
            } else {
                cStack.push(c);
            }
        }
        BoolExpr targetExpr = exprStack.pop();

        LOGGER.info("construct target Boolean expr : {}", targetExpr.toString());
        solver.add(targetExpr);
        /**
         * 订阅主题
         * */
        if (!subscribed) {
            subscribed = true;
            SubscribeUtil.subscribe(SubscribeUtil.TOPIC_TELEMTRY);
        }

        /**
         * 没有停止推理时，就反复获取事件，推理
         * */
        while (!stop) {

            boolean hasDeduced = false;
            boolean actuallyDeduced = false;

            /**
             * 将消息从buffer种取出， 构造成事件，根据设备+数据名构放入对应的双端阻塞队列中
             *
             *
             * */
            if (!canDeduceOnce()) {
                String msg;
                while ((msg = messageQueue.poll()) != null) {
                    LOGGER.info("consume one message from message queue, content :{}", msg);
                    String siteName = StringUtil.splitString(msg, "<siteName>", "</siteName>");
                    String deviceName = StringUtil.splitString(msg, "<deviceName>", "</deviceName>");
                    String dataName = StringUtil.splitString(msg, "<dataName>", "</dataName>");
                    String value = StringUtil.splitString(msg, "<detected_value>", "</detected_value>");
                    if (StringUtils.isEmpty(siteName)
                        || StringUtils.isEmpty(deviceName)
                        || StringUtils.isEmpty(dataName)
                        || StringUtils.isEmpty(value)) {
                        continue;
                    }
                    long time = System.currentTimeMillis();

                    PubSubEvent event = new PubSubEvent(deviceName, dataName, siteName, time, Double.parseDouble(value));
                    if (!eventConstraint.inRange(event.getDeviceName(), event.getDataName())) {
                        continue;
                    }
                    if (eventConstraint.notValidValue(event)) {
                        continue;
                    }
                    Deque<PubSubEvent> buffer = eventBuffer.getOrDefault(event.getDeviceNameAndDataName(), new LinkedBlockingDeque<>());
                    buffer.offer(event);
                    LOGGER.info("put message into event queue, queue name : {}", event.getDeviceNameAndDataName());
                }
            } else {
                /**
                 * 已经将所有消息转化为事件放置到eventBuffer中， 判断合理后可以开始推理
                 * */
                actuallyDeduced = _deduce(messageMapToKnowledgeUri, uriToFunction, complexId);
                hasDeduced = true;
            }
            /**
             * 移除每个队列的队头元素,继续进行下一轮推理
             * */
            if (hasDeduced || actuallyDeduced) clearBuffer();
            if (!hasDeduced || !actuallyDeduced) Thread.yield();
        }

        LOGGER.info("deducer task stop!");
    }

    /**
     * 开始推理
     * */
    private boolean _deduce(Map<String, String> messageMapToKnowledgeUri, Map<String, FuncDecl> uriToFunction, Long complexId) {

        PubSubEvent left = null, right = null;

        // 例如具有一个完整的复杂事件，包括两个原子事件中的属性
        solver.push();
        for (String key : eventBuffer.keySet()) {
            Deque<PubSubEvent> buffer = eventBuffer.get(key);
            BoolExpr expr = context.mkBool(false);
            int cnt = buffer.size();
            for (int i = 0; i < cnt; ++i) {
                PubSubEvent event = buffer.removeFirst();
                if (i == 0 ) left = event;
                if (i == cnt-1) right = event;
                expr = context.mkOr(
                    expr,
                    context.mkFPEq(
                        (FPExpr) context.mkApp(uriToFunction.get(messageMapToKnowledgeUri.get(event.getDeviceNameAndDataName()))
                            ,context.MkString(event.getLocation())
                            , context.mkInt(event.getTime()))
                        , context.mkFP(event.getValue(), context.mkFPSortDouble())));
                buffer.offer(event);
            }
            solver.add(expr);
        }

        if (!(left == begin && right == end)) {
            LOGGER.info("begin deduce once...");
            Status result = solver.check();
            if (result == Status.SATISFIABLE) {
                LOGGER.info("deduce result : complex event found!!!!!!!!!!!!!!!!!!!");
                deduceContext.foundComplexEvent(complexId);
                String pubMessage = "<SYSTEM_CATE>"+complexId+1 + "</SYSTEM_CATE>";
                PublishUtil.publish(PublishUtil.LINK_FAILURE, pubMessage);
                LOGGER.info("publish message, topic :" + PublishUtil.LINK_FAILURE + ", message" + pubMessage);
            } else {
                LOGGER.info("deduce result : no complex event");
            }
            LOGGER.info("end deduce once...");
            begin = left;
            end = right;
            solver.pop();
            return true;
        } else {
            begin = left;
            end = right;
            solver.pop();
            return false;
        }
    }

    private void clearBuffer() {

        long now = System.currentTimeMillis();

        for (String deviceNameAndDataName : eventBuffer.keySet()) {
            Deque<PubSubEvent> buffer = eventBuffer.get(deviceNameAndDataName);

            if (useDefaultWindowPolicy) {
                while ((buffer.size() > 0) && (now - buffer.peekFirst().getTime() > DEFAULT_TIME_WINDOW))
                    buffer.removeFirst();
            } else {
                if (useTimeWindow) {
                    while ((buffer.size() > 0) && (now - buffer.peekFirst().getTime() > timeWindow))
                        buffer.removeFirst();
                } else {
                    while ((buffer.size() > 0) && (buffer.size() > lenWindow))
                        buffer.removeFirst();
                }
            }
        }
    }


    /**
     * 根据定义的时间窗口或长度窗口，判断是否可以进行推理一次
     * */
    private boolean canDeduceOnce() {
        // 默认时间窗口
        if (useDefaultWindowPolicy) {
            for (String deviceNameAndDataName : eventBuffer.keySet()) {
                if (eventBuffer.get(deviceNameAndDataName).isEmpty()) {
                    return false;
                }
            }
            return true;
        } else {
            if (useTimeWindow) {
                for (String deviceNameAndDataName : eventBuffer.keySet()) {
                    if (eventBuffer.get(deviceNameAndDataName).isEmpty()) {
                        return false;
                    }
                }
                return true;
            } else {
                for (String deviceNameAndDataName : eventBuffer.keySet()) {
                    if (eventBuffer.get(deviceNameAndDataName).size() == 0) {
                        return false;
                    }
                }
                return true;
            }
        }
    }

    /**
     * 当队列满了，发布订阅线程会阻塞,不晓得有没有影响
     * */
    public void receiveMessage(String msg) {
        try {
            messageQueue.put(msg);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void shutDown() {
        this.stop = true;
        this.userNotificationProcessImpl.deRegisterDeducer(this, SubscribeUtil.TOPIC_TELEMTRY);
    }


    // 加载所有的事件类
    private void loadClass(Map<KnowledgeMetaEvent, List<MetaEventAttrRelation>> eventWithAttrRelation) {

        Map<String, Class<?>> topicWithClass = ClassUtil.generateMetaEventClassWithAttrRelation(eventWithAttrRelation);
        topicWithClass.forEach( (k,v) -> cachedTopicClasses.put(k,v));
        LOGGER.info("generate all subEvent class, result:{}", JSONObject.toJSONString(topicWithClass));
    }

    public Class<?> getClassByTopic(String topic) {
        return this.cachedTopicClasses.get(topic);
    }

    public static void main(String[] args) {

        /**
         * http://www.w3.org/ns/sosa/EDFA中继器/输入功率
         *
         * 属性1， 设备名
         * 属性2， 地点
         * 属性3， 时间
         *
         * 目标：  输入功率 大于10
         *
         * */

//        Context context = new Context();
//        Solver solver = context.mkSolver();
//
//        Sort stringSort = context.mkStringSort();
//        Sort intSort = context.mkIntSort();
//
//
//        IntExpr val = context.mkIntConst("val");
//
//
//        FuncDecl funcDecl = context.mkFuncDecl("http://www.w3.org/ns/sosa/EDFA中继器/输入功率", new Sort[]{stringSort, stringSort}, intSort);
//        System.out.println(funcDecl);
//
//
//        BoolExpr boolExpr1 = context.mkEq(
//            context.mkApp(funcDecl, context.MkString("EDFA"), context.MkString("beijing"))
//            ,  context.mkInt(11));
//
//
//        BoolExpr boolExpr2 = context.mkEq(
//            context.mkApp(funcDecl, context.MkString("EDFA"), context.MkString("shanghai"))
//            ,  context.mkInt(12));
//        //System.out.println(boolExpr1.toString());
//
//        BoolExpr boolExpr3 = context.mkAnd(boolExpr1, boolExpr2);
//        solver.add(boolExpr3);
//
//        Expr exprx = context.mkConst("x", stringSort);
//        Expr expry = context.mkConst("y", stringSort);
//        Quantifier quantifier = context.mkForall(new Expr[]{exprx, expry}, context.mkGt((ArithExpr)context.mkApp(funcDecl, exprx, expry), context.mkInt(11)), 1, null, null, context.mkSymbol("a"), context.mkSymbol("b"));
//
//        solver.add(quantifier);
//
//        Status check = solver.check();
//        System.out.println(check.toString());
//        Model model = solver.getModel();
//
//
//        System.out.println();
//        System.out.println(model);

//        String msg = "<resource><site_name>" + "siteName" + "</site_name>" +
//            "<device_name>" + "device_name" +"</device_name>" +
//            "<data_name>" + "data_name" + "</data_name>" + "</resource>" +
//            "<value><value>" + "fValue" + "</value>" +
//            "<type>" + "fault_type" + "</type>" +
//            "<timestamp>" + "time" + "</timestamp></value>";
//        String s = StringUtil.splitString(msg, "<resource><site_name>", "</site_name>");
//        System.out.println(s);
//
//        String dateTimeStr = "2020/04/21 20:49:40.313";
//        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS");
//        TemporalAccessor parse = df.parse(dateTimeStr);
//        System.out.println();

        Context context = new Context();
        Sort stringSort = context.mkStringSort();
        Sort intSort = context.mkIntSort();
        Sort doubleSort = context.mkFPSortDouble();

        String s = "((17)&(18))&((15)|(16))";
        s = '(' + s + ')';
        Map<Integer, BoolExpr> map = new HashMap<>();

        FuncDecl f1 = context.mkFuncDecl("http://www.w3.org/ns/sosa/光频_输入功率", new Sort[]{stringSort, intSort}, doubleSort);
        FuncDecl f2 = context.mkFuncDecl("http://www.w3.org/ns/sosa/光频_输出功率", new Sort[]{stringSort, intSort}, doubleSort);

//        FuncDecl f1 = context.mkFuncDecl("http://www.w3.org/ns/sosa/光频_输入功率", new Sort[]{stringSort, intSort}, intSort);
//        FuncDecl f2 = context.mkFuncDecl("http://www.w3.org/ns/sosa/光频_输出功率", new Sort[]{stringSort, intSort}, intSort);

        Expr exprx1 = context.mkConst("x1", stringSort);
        Expr expry1 = context.mkConst("y1", intSort);
        Expr exprx2 = context.mkConst("x2", stringSort);
        Expr expry2 = context.mkConst("y2", intSort);

        Quantifier f1quantifier1 = context.mkForall(new Expr[]{exprx1, expry1}, context.mkFPGt((FPExpr) context.mkApp(f1, exprx1, expry1), context.mkFP(-13.0, context.mkFPSortDouble())), 1, null, null, null, null);
        Quantifier f1quantifier2 = context.mkForall(new Expr[]{exprx1, expry1}, context.mkFPLt((FPExpr) context.mkApp(f1, exprx1, expry1), context.mkFP(-16.0, context.mkFPSortDouble())), 1, null, null, null, null);
        Quantifier f2quantifier1 = context.mkForall(new Expr[]{exprx1, expry1}, context.mkFPGEq((FPExpr)context.mkApp(f2, exprx2, expry2), context.mkFP(1.0, context.mkFPSortDouble())), 1, null, null, null, null);
        Quantifier f2quantifier2 = context.mkForall(new Expr[]{exprx1, expry1}, context.mkFPLEq((FPExpr)context.mkApp(f2, exprx2, expry2), context.mkFP(4.0, context.mkFPSortDouble())), 1, null, null, null, null);

//        Quantifier f1quantifier1 = context.mkForall(new Expr[]{exprx1, expry1}, context.mkGt((ArithExpr) context.mkApp(f1, exprx1, expry1), context.mkInt(-13)), 1, null, null, null, null);
//        Quantifier f1quantifier2 = context.mkForall(new Expr[]{exprx1, expry1}, context.mkLt((ArithExpr)context.mkApp(f1, exprx1, expry1), context.mkInt(-16)), 1, null, null, null, null);
//        Quantifier f2quantifier1 = context.mkForall(new Expr[]{exprx1, expry1}, context.mkGe((ArithExpr)context.mkApp(f2, exprx2, expry2), context.mkInt(1)), 1, null, null, null, null);
//        Quantifier f2quantifier2 = context.mkForall(new Expr[]{exprx1, expry1}, context.mkLe((ArithExpr)context.mkApp(f2, exprx2, expry2), context.mkInt(4)), 1, null, null, null, null);
//
        map.put(15, f1quantifier1);
        map.put(16, f1quantifier2);
        map.put(17, f2quantifier1);
        map.put(18, f2quantifier2);


        char[] arr = s.toCharArray();
        Stack<Character> cStack = new Stack<>();
        Stack<BoolExpr> exprStack = new Stack<>();
        for (char c : arr) {
            if (c == ')') {
                String tmp = "";
                while (cStack.peek() != '(') {
                    tmp = cStack.pop() + tmp;
                }
                cStack.pop();
                if (tmp.contains("&")) {
                    Integer left = Integer.parseInt(tmp.split("&")[0]);
                    Integer right = Integer.parseInt(tmp.split("&")[0]);
                    BoolExpr boolExpr = context.mkAnd(exprStack.pop(), exprStack.pop());
                    String newNum = left + "" + right;
                    map.put(Integer.parseInt(newNum), boolExpr);
                    exprStack.push(boolExpr);
                    for (char i : newNum.toCharArray()) {
                        cStack.push(i);
                    }
                } else if(tmp.contains("|")) {
                    Integer left = Integer.parseInt(tmp.split("\\|")[0]);
                    Integer right = Integer.parseInt(tmp.split("\\|")[0]);
                    BoolExpr boolExpr = context.mkOr(exprStack.pop(), exprStack.pop());
                    String newNum = left + "" + right;
                    map.put(Integer.parseInt(newNum), boolExpr);
                    exprStack.push(boolExpr);
                    for (char i : newNum.toCharArray()) {
                        cStack.push(i);
                    }
                } else {
                    for (char i : tmp.toCharArray()) {
                        cStack.push(i);
                    }
                    exprStack.push(map.get(Integer.parseInt(tmp)));
                }
            } else {
                cStack.push(c);
            }
        }
        BoolExpr expr = exprStack.pop();
        Solver solver = context.mkSolver();
        solver.add(expr);
        BoolExpr event1 = context.mkFPEq((FPExpr) context.mkApp(f1, context.MkString("beijing"), context.mkInt(1)), context.mkFP(-17.0, context.mkFPSortDouble()));
        BoolExpr event2 = context.mkFPEq((FPExpr) context.mkApp(f2, context.MkString("beijing"), context.mkInt(2)), context.mkFP(3.0, context.mkFPSortDouble()));

//        BoolExpr event1 = context.mkEq(context.mkApp(f1, context.MkString("beijing"), context.mkInt(1)), context.mkInt(-14));
//        event1 = context.mkOr(event1, context.mkEq(context.mkApp(f1, context.MkString("beijing"), context.mkInt(3)), context.mkInt(-14)));
//        event1 = context.mkOr(event1, context.mkEq(context.mkApp(f1, context.MkString("beijing"), context.mkInt(4)), context.mkInt(-14)));
//        event1 = context.mkOr(event1, context.mkEq(context.mkApp(f1, context.MkString("beijing"), context.mkInt(5)), context.mkInt(-14)));
//        event1 = context.mkOr(event1, context.mkEq(context.mkApp(f1, context.MkString("beijing"), context.mkInt(6)), context.mkInt(-14)));
//        event1 = context.mkOr(event1, context.mkEq(context.mkApp(f1, context.MkString("beijing"), context.mkInt(7)), context.mkInt(-14)));
//        event1 = context.mkOr(event1, context.mkEq(context.mkApp(f1, context.MkString("beijing"), context.mkInt(8)), context.mkInt(-14)));
//        event1 = context.mkOr(event1, context.mkEq(context.mkApp(f1, context.MkString("beijing"), context.mkInt(9)), context.mkInt(-14)));
//        event1 = context.mkOr(event1, context.mkEq(context.mkApp(f1, context.MkString("beijing"), context.mkInt(10)), context.mkInt(-14)));
//        event1 = context.mkOr(event1, context.mkEq(context.mkApp(f1, context.MkString("beijing"), context.mkInt(11)), context.mkInt(-14)));
//        event1 = context.mkOr(event1, context.mkEq(context.mkApp(f1, context.MkString("beijing"), context.mkInt(12)), context.mkInt(-14)));
//
//
//        BoolExpr event2 = context.mkEq(context.mkApp(f2, context.MkString("beijing"), context.mkInt(2)), context.mkInt(2));
//        event2 = context.mkOr(event2, context.mkEq(context.mkApp(f2, context.MkString("beijing"), context.mkInt(3)), context.mkInt(2)));
//        event2 = context.mkOr(event2, context.mkEq(context.mkApp(f2, context.MkString("beijing"), context.mkInt(4)), context.mkInt(2)));
//        event2 = context.mkOr(event2, context.mkEq(context.mkApp(f2, context.MkString("beijing"), context.mkInt(5)), context.mkInt(2)));
//        event2 = context.mkOr(event2, context.mkEq(context.mkApp(f2, context.MkString("beijing"), context.mkInt(6)), context.mkInt(2)));
//        event2 = context.mkOr(event2, context.mkEq(context.mkApp(f2, context.MkString("beijing"), context.mkInt(7)), context.mkInt(2)));
//        event2 = context.mkOr(event2, context.mkEq(context.mkApp(f2, context.MkString("beijing"), context.mkInt(8)), context.mkInt(2)));
//        event2 = context.mkOr(event2, context.mkEq(context.mkApp(f2, context.MkString("beijing"), context.mkInt(9)), context.mkInt(2)));
//        event2 = context.mkOr(event2, context.mkEq(context.mkApp(f2, context.MkString("beijing"), context.mkInt(10)), context.mkInt(2)));
//        event2 = context.mkOr(event2, context.mkEq(context.mkApp(f2, context.MkString("beijing"), context.mkInt(14)), context.mkInt(2)));
//        event2 = context.mkOr(event2, context.mkEq(context.mkApp(f2, context.MkString("beijing"), context.mkInt(13)), context.mkInt(2)));
//        event2 = context.mkOr(event2, context.mkEq(context.mkApp(f2, context.MkString("beijing"), context.mkInt(12)), context.mkInt(2)));
//        event2 = context.mkOr(event2, context.mkEq(context.mkApp(f2, context.MkString("beijing"), context.mkInt(11)), context.mkInt(2)));

        solver.add(event1);
        solver.add(event2);
        for (BoolExpr boolExpr : solver.getAssertions()) {
            System.out.println(boolExpr);
        }
        Status check = solver.check();
        System.out.println(check);
    }


}
