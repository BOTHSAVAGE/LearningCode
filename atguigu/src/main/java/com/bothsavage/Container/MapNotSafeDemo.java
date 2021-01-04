package com.bothsavage.Container;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class MapNotSafeDemo {

    public static void main(String[] args) {
//        TestConcurrentModificationException();
//        TestConcurrentHashMap();
//        TestSynchronizedMap();

    }

    private static void TestSynchronizedMap() {
        Map<String,String> map = Collections.synchronizedMap(new HashMap<>());
        for(int i=0;i<30;i++){
            new Thread(()->{
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }

    private static void TestConcurrentHashMap() {
        Map<String,String> map = new ConcurrentHashMap<>();
        for(int i=0;i<30;i++){
            new Thread(()->{
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }

    private static void TestConcurrentModificationException() {
        Map<String,String> map = new HashMap<>();
        for(int i=0;i<30;i++){
            new Thread(()->{
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }
}
