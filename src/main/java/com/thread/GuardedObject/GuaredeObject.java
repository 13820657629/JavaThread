package com.thread.GuardedObject;

public class GuaredeObject {
    //结果
    private Object response;

    /**
     * 获取结果
     * @param timeout 表示要等待多久
     * @return
     */
    public Object get(long timeout){
        synchronized (this){
            long begin = System.currentTimeMillis();
            long passedTime = 0;
            while(response == null){
                long waitTime = timeout - passedTime;
                if(waitTime <= 0){
                    break;
                }
                try {
                    this.wait(waitTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                passedTime = System.currentTimeMillis() - begin;
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
