AQS全程是AbstractQueuedSynchronized，是阻塞式锁和相关同步器工具的框架

特点：  
1. 用state属性来表示资源的状态（分独占模式和共享模式），子类需要定义和维护这个状态，控制如何获取锁和释放锁<br>
    (1). getState - 获取state状态  
    (2). getState - 设置state状态  
    (3). compareAndSetState - 乐观锁机制设置state状态  
    (4). 独占模式是只有一个线程能够访问资源，而共享模式可以允许多个线程访问资源
2. 提供了基于FIFO的等待队列，类似于Monitor的EntryList
3. 条件变量来实现等待、唤醒机制，支持多个条件变量，类似于Monitor的WaitSet

子类需要去实现一些方法
1. tryAcquire
2. tryRelease
3. tryAcquireShared
4. tryReleaseShared
5. isHeldExclusively

