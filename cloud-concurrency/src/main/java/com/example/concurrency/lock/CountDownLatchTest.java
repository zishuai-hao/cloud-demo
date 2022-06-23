package com.example.concurrency.lock;

import lombok.SneakyThrows;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchTest {
    private final static ExecutorService executorService = Executors.newFixedThreadPool(10);

    @SneakyThrows
    public static void main(String[] args) {
        final CountDownLatch countDownLatch = new CountDownLatch(3);
        executorService.submit(() -> {
            while (countDownLatch.getCount() > 0) {
                countDownLatch.countDown();
                System.out.println("开了一次");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        countDownLatch.await();
        System.out.println("开锁成功");
    }
}
