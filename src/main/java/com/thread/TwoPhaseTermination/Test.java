package com.thread.TwoPhaseTermination;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test {
    public static void main(String[] args) throws InterruptedException {
        test01();
        System.out.println("------------");
        //test02();
    }

    public static void test01() throws InterruptedException {
        TwoPhaseTermination tpt = new TwoPhaseTermination();
        tpt.start();
        Thread.sleep(3500);
        log.debug("停止监控");
        tpt.stop();
    }

    public static void test02() throws InterruptedException {
        TwoPhaseTermination2 tpt = new TwoPhaseTermination2();
        tpt.start();
        Thread.sleep(3500);
        log.debug("停止监控");
        tpt.stop();
    }
}
