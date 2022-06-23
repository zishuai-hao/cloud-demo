package com.example.concurrency.lock;

import lombok.SneakyThrows;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 * 同时加持读锁，可以同时读
 * 写锁开始抢占，无法加持读锁
 */
public class RWLockTest {
    private static ReentrantReadWriteLock rwLock;
    private static Integer i = 100;
    private final static ExecutorService executorService = Executors.newFixedThreadPool(10);
    public static void main(String[] args) {
        rwLock = new ReentrantReadWriteLock(false);
        executorService.submit(() -> {
            write(Thread.currentThread());
        });
        executorService.submit(() -> {
            read(Thread.currentThread());
        });
        executorService.submit(() -> {
            read(Thread.currentThread());
        });
    }

    @SneakyThrows
    private static void read(Thread thread) {
        System.out.println("抢占读锁中～");
        rwLock.readLock().lock();
        Thread.sleep(1000);
        System.out.println("读线程 "+ thread.getName() + " 开始执行, i=" + i);
//        rwLock.readLock().unlock();
    }

    private static void write(Thread thread) {
        System.out.println("抢占写锁中～");
        rwLock.writeLock().lock();
        System.out.println("写线程 "+ thread.getName() + " 开始执行, i=" + ++i);
        rwLock.writeLock().unlock();
    }
}
