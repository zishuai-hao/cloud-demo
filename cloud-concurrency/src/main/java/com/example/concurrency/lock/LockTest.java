package com.example.concurrency.lock;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockTest {
    public static void main(String[] args) {
        final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock(true);
        final ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        readLock.lock();
        final ReentrantLock reentrantLock = new ReentrantLock();
    }
}
