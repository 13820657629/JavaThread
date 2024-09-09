package com.thread.GuardedObject;

import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.io.IOException;
import java.util.List;

@Slf4j
public class test {
    public static void main(String[] args) {
        GuaredeObject guaredeObject = new GuaredeObject();
        new Thread(() -> {
            log.debug("begin");
            Object response = guaredeObject.get(2000);
            log.debug("结果是:{}", response);
        }, "t1").start();

        new Thread(() -> {
            log.debug("begin");
            try {
                Thread.sleep(1000);
                guaredeObject.complete(null);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t2").start();
    }
}
