package com.thread.JUC.AQS;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;

//同步器类
public class MySync extends AbstractQueuedSynchronizer {


    @Override
    protected boolean tryAcquire(int arg){
        if(compareAndSetState(0,1)){
            //加上了锁，并设置owner为当前线程
            setExclusiveOwnerThread(Thread.currentThread());
            return true;
        }
        return false;
    }

    @Override
    protected boolean tryRelease(int arg){
        setExclusiveOwnerThread(null);
        setState(0);
        return true;
    }

    //是否持有独占锁
    @Override
    protected boolean isHeldExclusively(){
        return getState() == 1;
    }

    public Condition newCondition(){
        return new ConditionObject();
    }
}
