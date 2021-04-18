package com.rcd.iotsubsys.service.deduce;

import io.netty.channel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: iot-knowledge-sub-system
 * @description: ${description}
 * @author: liyi
 * @create: 2021-01-27 22:00
 */
public class CamputeHandler extends SimpleChannelInboundHandler<CamputeResponse> {

    private static final Logger logger = LoggerFactory.getLogger(CamputeHandler.class);

    private static int clientCnt = 0;

    private static final int CLIENTNUM = 5;

    private Map<String, Channel> channelMap = new HashMap<>();

    private ScheduleServer client;


    public CamputeHandler(ScheduleServer client) {
        this.client = client;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("connect success!! compute server address:" + ctx.channel().remoteAddress().toString());
        super.channelActive(ctx);
    }

    @Override
    public void channelRead0(final ChannelHandlerContext ctx, final CamputeResponse response) throws Exception {
        if (!response.isSuccess()) {
            client.setUnsatisfied();
        } else {
            client.handle(ctx.channel(), response);
        }
    }

//    private Object handle(RpcRequest request) throws Throwable {
//        String className   = request.getClassName();
//        Object serviceBean = handlerMap.get(className);
//
//        Class<?>   serviceClass   = serviceBean.getClass();
//        String     methodName     = request.getMethodName();
//        Class<?>[] parameterTypes = request.getParameterTypes();
//        Object[]   parameters     = request.getParameters();
//
//        logger.debug(serviceClass.getName());
//        logger.debug(methodName);
//        for (int i = 0; i < parameterTypes.length; ++i) {
//            logger.debug(parameterTypes[i].getName());
//        }
//        for (int i = 0; i < parameters.length; ++i) {
//            logger.debug(parameters[i].toString());
//        }
//
//        // JDK reflect
//        /*Method method = serviceClass.getMethod(methodName, parameterTypes);
//        method.setAccessible(true);
//        return method.invoke(serviceBean, parameters);*/
//
//        // Cglib reflect
//        FastClass serviceFastClass = FastClass.create(serviceClass);
////        FastMethod serviceFastMethod = serviceFastClass.getMethod(methodName, parameterTypes);
////        return serviceFastMethod.invoke(serviceBean, parameters);
//        // for higher-performance
//        int methodIndex = serviceFastClass.getIndex(methodName, parameterTypes);
//        return serviceFastClass.invoke(methodIndex, serviceBean, parameters);
//    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        logger.error("server caught exception", cause);
        ctx.close();
    }
}
