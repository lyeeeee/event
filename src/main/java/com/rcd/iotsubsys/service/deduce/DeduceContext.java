package com.rcd.iotsubsys.service.deduce;

import com.rcd.iotsubsys.domain.event.ComplexEvent;
import com.rcd.iotsubsys.domain.event.ComplexEventFound;
import com.rcd.iotsubsys.domain.knowledge.KnowledgeComplexEvent;
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

    public static BlockingQueue<KnowledgeComplexEvent> complexEventFounded = new LinkedBlockingQueue<>();

    @Autowired
    private ComplexEventService complexEventService;

    @Autowired
    private MetaEventService metaEventService;

    @Autowired
    private UserNotificationProcessImpl userNotificationProcessImpl;


    public void foundComplexEvent(Long complexEventId) {
        KnowledgeComplexEvent data = (KnowledgeComplexEvent) this.complexEventService.getComplexEventById(complexEventId).getData();
        complexEventFounded.offer(data);
    }

    public synchronized void begainDeduce(Long complexId) {
        Deducer deducer = deducers.get(complexId);
        if (deducer == null) {
            deducer = new Deducer(complexId, userNotificationProcessImpl, complexEventService, metaEventService, this);
            deducers.put(complexId, deducer);
            pool.submit(deducer);
            LOGGER.info("create one deducer, begin deduce");
        } else {
            LOGGER.info("deduce task exists");
        }

    }

    public synchronized void stopDeduce(Long complexEventId) {
        if (deducers.get(complexEventId) != null) {
            deducers.get(complexEventId).shutDown();
            deducers.remove(complexEventId);
        }
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
