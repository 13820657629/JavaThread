package com.thread.GuardedObject.version2;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Postman extends Thread{

    private int id;

    private String mail;

    public Postman(int id, String mail) {
        this.id = id;
        this.mail = mail;
    }

    @Override
    public void run() {
        GuardedObject guardedObject = Mailboxes.getGuardedObjct(id);
        log.debug("送信 id:{}, 内容{}", id,mail);
        guardedObject.complete(mail);
    }
}
