package com.rcd.iotsubsys.service.deduce;

import com.alibaba.fastjson.JSON;
import com.rcd.iotsubsys.domain.event.PubSubEvent;
import com.rcd.iotsubsys.util.PublishUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: iot-knowledge-sub-system
 * @description: ${description}
 * @author: liyi
 * @create: 2021-01-28 16:32
 */
public class ScheduleServer {

    private static Logger logger = LoggerFactory.getLogger(ScheduleServer.class);

    public static final int SERVER_NUM = 5;

    public static String regex = "\\([\\u4E00-\\u9FA50-9\\-_a-zA-Z><=\\s].+?\\)";
    public static Pattern pattern = Pattern.compile(regex);


    public static final String SERVER1 = "127.0.0.1:11111";
    public static final String SERVER2 = "127.0.0.1:11112";
    public static final String SERVER3 = "127.0.0.1:11113";
    public static final String SERVER4 = "127.0.0.1:11114";
    public static final String SERVER5 = "127.0.0.1:11115";
    public static final String SERVER1_ADDR = "127.0.0.1";
    public static final String SERVER2_ADDR = "127.0.0.1";
    public static final String SERVER3_ADDR = "127.0.0.1";
    public static final String SERVER4_ADDR = "127.0.0.1";
    public static final String SERVER5_ADDR = "127.0.0.1";
    public static final int SERVER1_PORT = 11111;
    public static final int SERVER2_PORT = 11112;
    public static final int SERVER3_PORT = 11113;
    public static final int SERVER4_PORT = 11114;
    public static final int SERVER5_PORT = 11115;
    /**
     * 服务类
     */
    public Bootstrap bootstrap = new Bootstrap();

    /**
     * 会话
     */
    public List<Channel> channels = new ArrayList<>();

    /**
     * 引用计数     原子递增类
     */
    public final AtomicInteger index = new AtomicInteger();


    public static Map<String, Channel> serverMap = new HashMap<>();

    public static Map<Channel, Integer> channelIdMap = new HashMap<>();

    public volatile AtomicBoolean completed = new AtomicBoolean(false);

    private DeduceContext deduceContext;

    private Long complexEventId;

    private List<List<String>> targetList;

    private int cnt = 2;

    private ServerBootstrap serverBootstrap;

    private NioServerSocketChannel nioServerSocketChannel;

    private Set<ScheduleServer> servers;

    private Deducer deducer;

    public ScheduleServer(DeduceContext deduceContext, Long complexEventId, Set<ScheduleServer> servers, Deducer deducer) {
        this.deducer = deducer;
        this.servers = servers;
        this.deduceContext = deduceContext;
        this.complexEventId = complexEventId;
    }



    /**
     * 保存不同结点之间的共享变量
     *
     * value 对应一个长度为2的object数组，
     * 第一个是共享变量集合Set，第二个是验证过的变量集合（针对另外一个结点）
     * 如果两个set的size相同，说明当前节点同另一个结点的共享变量已经验证
     * */
    public Set<String>[][] shareVarTable = new HashSet[SERVER_NUM][SERVER_NUM];

    public boolean[][] shareVarValidState = new boolean[SERVER_NUM][SERVER_NUM];



    public void init() throws InterruptedException {

        //worker
        EventLoopGroup worker = new NioEventLoopGroup(1);

        //设置线程池
        bootstrap.group(worker);

        //设置socket工厂、
        bootstrap.channel(NioSocketChannel.class);

        //设置管道
        bootstrap.handler(new ChannelInitializer<Channel>() {

            @Override
            protected void initChannel(Channel ch) throws Exception {
                ch.pipeline().addLast(new CamputeEncoder(CamputeRequest.class));
                ch.pipeline().addLast(new CamputeDecoder(CamputeResponse.class));
                ch.pipeline().addLast(new CamputeHandler(ScheduleServer.this));
            }
        });

        ChannelFuture future = bootstrap.connect(SERVER1_ADDR, SERVER1_PORT);
        channels.add(future.channel());
        serverMap.put(SERVER1, future.channel());
        channelIdMap.put(future.channel(), 0);
        future.sync();

        future = bootstrap.connect(SERVER2_ADDR, SERVER2_PORT);
        channels.add(future.channel());
        serverMap.put(SERVER2, future.channel());
        channelIdMap.put(future.channel(), 1);
        future.sync();

        future = bootstrap.connect(SERVER3_ADDR, SERVER3_PORT);
        channels.add(future.channel());
        serverMap.put(SERVER3, future.channel());
        channelIdMap.put(future.channel(), 2);
        future.sync();

        future = bootstrap.connect(SERVER4_ADDR, SERVER4_PORT);
        channels.add(future.channel());
        serverMap.put(SERVER4, future.channel());
        channelIdMap.put(future.channel(), 3);
        future.sync();

        future = bootstrap.connect(SERVER5_ADDR, SERVER5_PORT);
        channels.add(future.channel());
        serverMap.put(SERVER5, future.channel());
        channelIdMap.put(future.channel(), 4);
        future.sync();
    }

    /**
     * 获取会话 （对外提供一个方法获取会话）
     */
    public Channel nextChannel(){
        // 获取一个可用的会话
        return getFirstActiveChannel(0);
    }


    private Channel getFirstActiveChannel(int count){
        //AtomicInteger index 是原子类递增，可以实现很均匀的分配
        Channel channel = channels.get(Math.abs(index.getAndIncrement() % channels.size()));
        // 连接有可能是断开，加入已经断开连接了，我们需要进行尝试重连
        if(!channel.isActive()){
            //重连
            reconnect(channel);
            if(count >= channels.size()){
                throw new RuntimeException("no can use channel");
            }
            return getFirstActiveChannel(count + 1);
        }
        return channel;
    }

    /**
     * 重连
     * @param channel
     */
    private void reconnect(Channel channel){
        synchronized(channel){
            // 已经不在这个数组的时候，已经重连过了
            if(channels.indexOf(channel) == -1){
                return ;
            }

            Channel newChannel = bootstrap.connect(channel.remoteAddress()).channel();
            channels.set(channels.indexOf(channel), newChannel);
        }
    }

    public void submitTask(Map<String, String> messageEventTable, String[] targets,
                           Map<String, Deque<PubSubEvent>> eventBuffer) {
        int targetPerServer = targets.length/SERVER_NUM;
        List<List<String>> list = new ArrayList<>();
        /**
         * 每个server的目标
         * */
        for (int i = 0;i < SERVER_NUM; ++i) list.add(new ArrayList<>());

        for (int i = 0; i < targets.length; i++) {
                list.get(i%SERVER_NUM).add(targets[i]);
        }
        this.targetList = list;
        for (int i = 0;i < this.targetList.size(); ++i) {
            if (targetList.get(i).size() == 0) {
                for (int j = 0;j < SERVER_NUM; ++j) {
                    shareVarValidState[i][j] = true;
                }
                for (int j = 0;j < SERVER_NUM; ++j) {
                    shareVarValidState[j][i] = true;
                }
            }
        }
        /**
         * 计算两两之间的共享变量
         * */
        for (int i = 0; i < list.size()-1; ++i) {
            Set<String> iVar = new HashSet<>();
            List<String> ilist = list.get(i);
            for (String s : ilist) {
                Matcher matcher = pattern.matcher(s);
                while (matcher.find()) {
                    String tmp =  matcher.group();
                    iVar.add(tmp.split(" ")[0].substring(1));
                }
            }
            for (int j = i;j < list.size(); ++j) {
                List<String> jlist = list.get(j);
                for (String s : jlist) {
                    Matcher matcher = pattern.matcher(s);
                    while (matcher.find()) {
                        String tmp =  matcher.group().split(" ")[0].substring(1);
                        if (iVar.contains(tmp)) {
                            if (shareVarTable[i][j] == null) {
                                shareVarTable[i][j] = new HashSet<>();
                            }
                            shareVarTable[i][j].add(tmp);
                            if (shareVarTable[j][i] == null) {
                                shareVarTable[j][i] = new HashSet<>();
                            }
                            shareVarTable[j][i].add(tmp);
                        } else {
                            shareVarValidState[i][j] = true;
                            shareVarValidState[j][i] = true;
                        }
                    }
                }
            }
        }
        /**
         *向计算节点提交任务
         * */
        for (Channel channel : channels) {
            CamputeRequest request = new CamputeRequest();
            request.setCalcForSelf(true);
            request.setRequestId(UUID.randomUUID().toString());
            request.setCommondType(0);
            request.setEvents(eventBuffer);
            request.setFormulas(list.get(channelIdMap.get(channel)));
            channel.writeAndFlush(request);
        }
    }

    public boolean completed() {
        return this.completed.get();
    }

    public static void main(String[] args) {
        String regex = "\\([\\u4E00-\\u9FA50-9\\-_a-zA-Z><=\\s].+?\\)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher("((光频输入事件-optical_input_power >-13)|(光频输入事件-optical_input_power <-18))");
        while (matcher.find()) {
            System.out.println("find");
            System.out.println(matcher.group());
        }
    }

    public void setSatisfied() {
        if (this.completed.get()) {
            return;
        }
        this.completed.set(true);
        logger.info("发现复杂事件！！！！");
        deduceContext.foundComplexEvent(this.complexEventId);
        CamputeRequest stopRequest = new CamputeRequest();
        stopRequest.setCommondType(1);
        for (Channel channel : channels) {
            channel.writeAndFlush(stopRequest);
        }
        removeSelf(this);
    }

    public void setUnsatisfied() {
        this.completed.set(true);
        logger.info("本次计算基于给定的事件集不满足！！！！");
        CamputeRequest stopRequest = new CamputeRequest();
        stopRequest.setCommondType(1);
        for (Channel channel : channels) {
            channel.writeAndFlush(stopRequest);
        }
        removeSelf(this);
    }

    private void removeSelf(ScheduleServer s) {
        this.servers.remove(s);
    }

    private boolean checkIfSatisfied() {
        if (this.deducer.getValid() == false) return false;
        logger.info("当前共享变量验证状态:{}", JSON.toJSONString(shareVarValidState));
        for (int i = 0; i < shareVarValidState.length; ++i) {
            for (int j = 0;j < shareVarValidState.length; ++j)  {
                if (i == j || shareVarValidState[i][j]) continue;
                else if (shareVarValidState[i][j] == false){
                    return false;
                }
            }
        }
        return true;
    }

    public void handle(Channel channel, CamputeResponse response) {
        if (checkIfSatisfied()) {
            this.setSatisfied();
            return;
        }


        /**
         * 此时说明计算成功了， 那么需要计算共享变量
         * */
        // 哪一个服务器
        int index = this.channelIdMap.get(channel);
        if (!response.isSuccess()) {
            setUnsatisfied();
            return;
        }
        boolean setShareTask = false;
        if (response.isSuccess()) {
            if (!response.isCalcForSelf()) {
                shareVarValidState[response.getCalcForIndex()][index] = true;
            } else {
                // 分配共享变量验证任务
                setShareTask = true;
                Set<String>[] peers = shareVarTable[index];
                boolean rest = true;
                for (int i = 0;i < peers.length; ++i) {
                    /**
                     * 当前结点同别的结点有共享变量
                     * 需要将共享变量对应的事件，发配给别的server，确定能否满足
                     * */
                    if (peers[i] != null && !peers[i].isEmpty() && shareVarValidState[index][i] == false) {
                        Channel tmp = channels.get(i);
                        CamputeRequest request = new CamputeRequest();
                        request.setCommondType(0);
                        request.setFormulas(this.targetList.get(i));
                        request.setRequestId(UUID.randomUUID().toString());
                        request.setEvents(response.getEvents());
                        request.setCalcForSelf(false);
                        request.setCalcForIndex(index);
                        tmp.writeAndFlush(request);
                    }
                }
            }
        }
       /**
         * 当前节点没有计算任务了，也没有共享变量了， 随机终止一个别的结点，重新分配计算目标
         * 给这两个结点
         * */
        if (!setShareTask) {
            killRandomServerAndReuse(index);
        }
    }
    /**
     * serverRest: 空闲的机器
     * 随机寻找一个机器进行重新分配计算任务
     * */
    private void killRandomServerAndReuse(int serverRest) {

    }
}
