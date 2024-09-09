package com.thread.GuardedObject.version2;

import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

public class Mailboxes {
    private static Map<Integer, GuardedObject> boxes = new Hashtable<>();

    private static int id = 1;
    //产生唯一的ID
    private static synchronized int generateId(){
        return id++;
    }

    public static GuardedObject getGuardedObjct(int id){
        return boxes.remove(id);
    }

    public static GuardedObject createGuardedObject(){
        GuardedObject go = new GuardedObject(generateId());
        boxes.put(go.getId(), go);
        return go;
    }

    public static Set<Integer> getIds(){
        return boxes.keySet();
    }

}
