package com.thread.DiningPhilosophers;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Philosopher extends Thread{
    Chopstick left;
    Chopstick right;

    public Philosopher(String name, Chopstick left, Chopstick right){
        super(name);
        this.left = left;
        this.right = right;
    }

    @SneakyThrows
    @Override
    public void run(){
        while(true){
            //尝试获得左手筷子
           if(left.tryLock()){
               try{
                   //尝试获得右手筷子
                   if(right.tryLock()){
                       try {
                           eat();
                       }finally {
                           right.unlock();
                       }
                   }
               }finally {
                   //释放自己手里的筷子
                   left.unlock();
               }

            }
        }
    }

    private void eat() throws InterruptedException {
        log.debug("eating....");
        Thread.sleep(1000);
    }
}
