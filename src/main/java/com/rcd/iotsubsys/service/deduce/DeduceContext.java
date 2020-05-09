package com.rcd.iotsubsys.service.deduce;

import com.rcd.iotsubsys.service.event.ComplexEventService;
import com.rcd.iotsubsys.service.event.MetaEventService;

import com.rcd.iotsubsys.wsn.subscribe.soap.wsn.UserNotificationProcessImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: iot-knowledge-sub-system
 * @description: ${description}
 * @author: liyi
 * @create: 2020-04-25 16:41
 */
@Component
public class DeduceContext {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeduceContext.class);

    private static Map<Long, Deducer> deducers = new HashMap<>();

    private static ExecutorService pool = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
        60L, TimeUnit.SECONDS,//60秒
        new SynchronousQueue<Runnable>(),
        new DeducerThreadFactory(),  new ThreadPoolExecutor.AbortPolicy());

    @Autowired
    private ComplexEventService complexEventService;

    @Autowired
    private MetaEventService metaEventService;

    @Autowired
    private UserNotificationProcessImpl userNotificationProcessImpl;


    public synchronized void begainDeduce(Long complexId) {
        Deducer deducer;
        if ((deducer = deducers.get(complexId)) == null) {
            deducer = new Deducer(complexId, userNotificationProcessImpl, complexEventService, metaEventService);
            deducers.put(complexId, deducer);
        }
        pool.submit(deducer);
        LOGGER.info("create one deducer, begin deduce");
    }

    static class DeducerThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        DeducerThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                Thread.currentThread().getThreadGroup();
            namePrefix = "deducer-pool-" +
                poolNumber.getAndIncrement() +
                "-thread-";
        }

        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                namePrefix + threadNumber.getAndIncrement(),
                0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }

}
