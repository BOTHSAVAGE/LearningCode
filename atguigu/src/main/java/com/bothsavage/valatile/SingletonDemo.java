package com.bothsavage.valatile;

public class SingletonDemo {

    private  static  volatile  SingletonDemo instance = null;

    private SingletonDemo(){
        System.out.println(Thread.currentThread().getName()+":我是构造方法");
    }

//    public static synchronized  SingletonDemo getInstance(){
//    public static  SingletonDemo getInstance(){
//        if(instance==null){
//            instance = new SingletonDemo();
//        }
//        return instance;
//    }

    //DCL双端检索机制
    public static  SingletonDemo getInstance(){
        if(instance==null){
            synchronized (SingletonDemo.class){
                if(instance==null){
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        //单线程(main线程的操作)
//        TestMainThread();

        //并发多线程(构造器执行多次)
        for(int i=0;i<10000;i++){
            new Thread(()->{
                SingletonDemo.getInstance();
            },String.valueOf(i)).start();
        }
    }

    private static void TestMainThread() {
        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
    }
}
