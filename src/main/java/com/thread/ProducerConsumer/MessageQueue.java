package com.thread.ProducerConsumer;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;

/**
 * 消息队列类，java线程之间通信
 */

@Slf4j
public class MessageQueue {
    //消息的队列集合
    private LinkedList<Message> list = new LinkedList<>();

    //消息的队列容量
    private int capcity;

    public MessageQueue(int capcity) {
        this.capcity = capcity;
    }

    //获取消息
    public Message take(){
        synchronized (list){
            while(list.isEmpty()){
                try {
                    log.debug("队列为空，消费者线程等待");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //从队列的头部获取消息并返回
            Message message = list.removeFirst();
            log.debug("已消费消息{}", message);
            list.notifyAll();
            return message;
        }
    }

    //存入消息
    public void put(Message message){
        synchronized (list){
            while(list.size() == capcity){
                try {
                    log.debug("队列已满，生产者线程等待");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //将消息加入到队列的尾部
            list.addLast(message);
            log.debug("已生产消息{}", message);
            list.notifyAll();
        }
    }
}
