package com.bothsavage.Container;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

public class SetNotSafeDemo {

    public static void main(String[] args) {
//        TestConcurrentModificationException();
//        TestSolveByCollections_SynchronizedSet();
//        TestCopyOnWriteArraySet();
        new HashSet<>();//底层数据结构就是hashmap
    }

    private static void TestCopyOnWriteArraySet() {
        Set<String> stringSet = new CopyOnWriteArraySet<>();
        for(int i=0;i<30;i++){
            new Thread(()->{
                stringSet.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(stringSet);
            },String.valueOf(i)).start();
        }
    }

    private static void TestSolveByCollections_SynchronizedSet() {
        Set<String> stringSet = Collections.synchronizedSet(new HashSet<>());
        for(int i=0;i<30;i++){
            new Thread(()->{
                stringSet.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(stringSet);
            },String.valueOf(i)).start();
        }
    }

    private static void TestConcurrentModificationException() {
        Set<String> stringSet = new HashSet<>();
        for(int i=0;i<30;i++){
            new Thread(()->{
                stringSet.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(stringSet);
            },String.valueOf(i)).start();
        }
    }
}
