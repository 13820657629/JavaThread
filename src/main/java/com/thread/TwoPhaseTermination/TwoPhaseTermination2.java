package com.thread.TwoPhaseTermination;

import lombok.extern.slf4j.Slf4j;

/**
 * 使用volatile实现两阶段中止模式
 */
@Slf4j
public class TwoPhaseTermination2 {
    private Thread monitor;

    //打断标记
    private volatile boolean stop;

   public void start(){
       monitor = new Thread(() -> {
           while(true){
               if(stop){
                   log.debug("料理后事");
                   break;
               }
               try {
                   Thread.sleep(1000);
                   log.debug("执行监控记录");
               } catch (InterruptedException e) {

               }
           }
       });

       monitor.start();
   }

   public void stop(){
        stop = true;
        //此处加interrupt是为了让线程在sleep睡眠的时候直接唤醒
        monitor.interrupt();
   }

}
