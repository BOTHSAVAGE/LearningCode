package com.bothsavage.Container;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListNotSafeDemo {
    public static void main(String[] args) {

//        TestGrowSize();
//        TestSingelThread();
//        TestConcurrentModificationException();
//        TestSolveByVector();
//        TestSolveBySynchronizedList();
//        TestSolveCopyOnWriteArrayList();


    }

    private static void TestSolveCopyOnWriteArrayList() {
        List<String> strings = new CopyOnWriteArrayList<>();
        for(int i=0;i<30;i++){
            new Thread(()->{
                strings.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(strings);
            },String.valueOf(i)).start();
        }
    }

    private static void TestSolveBySynchronizedList() {
        List<String> strings = Collections.synchronizedList(new ArrayList<>());
        for(int i=0;i<30;i++){
            new Thread(()->{
                strings.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(strings);
            },String.valueOf(i)).start();
        }
    }

    private static void TestSolveByVector() {
        List<String> strings = new Vector<>();
        for(int i=0;i<10;i++){
            new Thread(()->{
                strings.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(strings);
            },String.valueOf(i)).start();
        }
    }

    private static void TestConcurrentModificationException() {
        List<String> strings = new ArrayList<>();
        for(int i=0;i<30;i++){
            new Thread(()->{
                strings.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(strings);
            },String.valueOf(i)).start();
        }
        //并发修改的异常 java.util.ConcurrentModificationException
    }

    private static void TestSingelThread() {
        List<String> strings = Arrays.asList("1","2","3");
        strings.forEach(System.out::println);
    }

    private static void TestGrowSize() {
        //本质是一个动态数组，初始值是10，扩容是len*3/2（小数点右移）
        new ArrayList<Integer>();
    }
}
