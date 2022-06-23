package com.example.concurrency.pool;

import java.util.concurrent.*;

public class MyThreadPool {
    public static void main(String[] args) {
        int corePoolSize = 10;
        int maxPollSize = 100;
        long keepAlive = 30 * 1000;
        final TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        final LinkedBlockingDeque<Runnable> runnables = new LinkedBlockingDeque<>();

        // 拒绝策略
        final RejectedExecutionHandler rejectedExecutionHandler = (r, executor) -> {
        };

        getThreadPool(corePoolSize, maxPollSize, keepAlive, timeUnit, runnables, rejectedExecutionHandler);

    }

    public static void getThreadPool(int corePoolSize, int maxPollSize, long keepAlive, TimeUnit timeUnit, LinkedBlockingDeque<Runnable> runnables, RejectedExecutionHandler rejectedExecutionHandler) {
        final ExecutorService threadPoolExecutor = new ThreadPoolExecutor(
                corePoolSize,
                maxPollSize,
                keepAlive,
                timeUnit,
                runnables,
                Executors.defaultThreadFactory(),
                rejectedExecutionHandler
        );
    }
}
