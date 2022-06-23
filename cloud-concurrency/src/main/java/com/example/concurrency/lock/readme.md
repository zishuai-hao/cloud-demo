# 锁

## Lock
## AQS
AbstractQueuedSynchronizer

## ReentrantLock
可重入锁
## ReadWriteLock
读写锁
* 同时加持读锁，可以同时读
* 写锁开始抢占，无法加持读锁
## CountDownLatch
倒计时锁
https://www.jianshu.com/p/128476015902
countDown 计数器减去1
await 等待计数器到达0 获取

## Semaphore
## CyclicBarrier