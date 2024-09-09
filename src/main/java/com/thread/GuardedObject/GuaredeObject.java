package com.thread.GuardedObject;

public class GuaredeObject {
    //结果
    private Object response;

    //获取结果
    public Object get(){
        synchronized (this){
            //没有结果
           while(response == null){
               try {
                   this.wait();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
           return response;
        }
    }
    
    //产生结果
    public void complete(Object response){
        synchronized (this){
            //给结果成员变量赋值
            this.response = response;
            this.notifyAll();
        }
    }


}
