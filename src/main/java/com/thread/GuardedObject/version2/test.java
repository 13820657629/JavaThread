package com.thread.GuardedObject.version2;

import java.util.concurrent.TimeUnit;

public class test {
    public static void main(String[] args) throws InterruptedException {
        for(int i = 0; i < 3; i++){
            new People().start();
        }

        TimeUnit.SECONDS.sleep(1);
        for(Integer id : Mailboxes.getIds()){
            new Postman(id, "内容" + id).start();
        }
    }
}
