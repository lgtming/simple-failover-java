/**
 * 
 */
package com.github.phantomthief.failover.util;

import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

/**
 * @author w.vela
 */
public class SharedCheckExecutorHolder {

    private static final int THREAD_COUNT = 10;

    private static class LazyHolder {

        private static final ScheduledExecutorService INSTANCE = new ScheduledThreadPoolExecutor(
                THREAD_COUNT,
                new ThreadFactoryBuilder().setNameFormat("scheduled-failover-recovery-check-%d")
                        .setPriority(Thread.MIN_PRIORITY).setDaemon(true).build()) {

            public void shutdown() {
                throw new UnsupportedOperationException();
            };

            public List<Runnable> shutdownNow() {
                throw new UnsupportedOperationException();
            };
        };
    }

    public static ScheduledExecutorService getInstance() {
        return LazyHolder.INSTANCE;
    }
}