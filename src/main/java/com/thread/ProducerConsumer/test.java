package com.thread.ProducerConsumer;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class test {
    public static void main(String[] args) throws InterruptedException {
        MessageQueue messageQueue = new MessageQueue(2);
        for(int i = 0; i < 3; i++){
            int id = i;
            new Thread(() -> {
                messageQueue.put(new Message(id, "值" + id));
            }, "生产者" + i).start();
        }

        new Thread(() -> {
            while(true){
                try {
                    TimeUnit.SECONDS.sleep(1);
                    Message message = messageQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "消费者").start();
    }
}
