package com.example.concurrency.lock;

import lombok.SneakyThrows;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    private final static ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        final ReentrantLock reentrantLock = new ReentrantLock();
        executorService.submit(() -> {
            run(reentrantLock);
        });
        executorService.submit(() -> {
            run(reentrantLock);
        });
    }

    @SneakyThrows
    private static void run(ReentrantLock reentrantLock) {
        reentrantLock.lock();
        System.out.println("执行内容");
        Thread.sleep(5000);
        reentrantLock.unlock();
    }
}
