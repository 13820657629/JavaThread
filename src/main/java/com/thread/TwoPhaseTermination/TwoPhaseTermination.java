package com.thread.TwoPhaseTermination;

import lombok.extern.slf4j.Slf4j;

/**
 * 不使用volatile来实现二阶段终止模式
 */
@Slf4j
public class TwoPhaseTermination {
    private Thread monitor;

    public void start(){
        monitor = new Thread(() -> {
           while(true){
               Thread currentThread = Thread.currentThread();
               if(currentThread.isInterrupted()){
                   log.debug("料理后事");
                   break;
               }
               try {
                   Thread.sleep(1000);
                   log.debug("执行监控记录");
               } catch (InterruptedException e) {
                   e.printStackTrace();
                   //必须加interrupt，线程在sleep时被打断，会被catch捕获异常，并且会重置打断标记
                   currentThread.interrupt();
               }
           }
        });

        monitor.start();
    }

    public void stop(){
        monitor.interrupt();
    }
}
