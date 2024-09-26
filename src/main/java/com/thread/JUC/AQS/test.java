package com.thread.JUC.AQS;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Lock;

import static java.lang.Thread.sleep;

@Slf4j
public class test {
    public static void main(String[] args) {
        MyLock lock = new MyLock();
        new Thread(() -> {
            lock.lock();
            try{
                log.debug("locking");
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                log.debug(("unlocking"));
                lock.unlock();
            }
        }, "t1").start();

        new Thread(() -> {
            lock.lock();
            try{
                log.debug("locking");
            }finally {
                log.debug(("unlocking"));
                lock.unlock();
            }
        }, "t2").start();
    }
}
