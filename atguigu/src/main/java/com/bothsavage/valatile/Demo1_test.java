package com.bothsavage.valatile;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile 可以保证数据的可见性，无法保证数据的原子性
 * synchronized 获取锁后会copy主存到工作内存，释放锁后会刷新主存
 */
public class Demo1_test {

    volatile  int number = 0;
//    int number = 0;

    AtomicInteger atomicInteger = new AtomicInteger();

    public void add60(){
        this.number = 60;
    }


    public void addPlusPlus(){
//    public synchronized  void addPlusPlus(){
        /**
         * 通过jclasslib可以知道
         * n++一共有三个指令
         * getfiled 拿到原始值
         * iadd ++
         * putfiled 写回
         */
        this.number++;
    }

    public void addAtoPlusPlus(){
        atomicInteger.getAndIncrement();
    }



//--------------------------上面都是业务逻辑，下面为测试--------------------------------------
    public static void main(String[] args) {
        //1.可见性测试
//        testSeeByVolatile();
        //2.不保证原子性测试
//        testNotAto();
        //3.如何去解决原子操作
        testSolveAto();

    }

    private static void testSolveAto() {
        Demo1_test demo1_test = new Demo1_test();
        for(int i=0;i<2000;i++){
            new Thread(()->{
                for(int j = 0;j<100000;j++){
                    demo1_test.addAtoPlusPlus();
                }
            },String.valueOf(i)).start();
        }
        while (Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+"/t ato integer "+demo1_test.atomicInteger);
    }

    private static void testNotAto() {
        Demo1_test demo1_test = new Demo1_test();
        for(int i=0;i<200;i++){
            new Thread(()->{
                for(int j=0;j<10000;j++){
                    demo1_test.addPlusPlus();
                }
            },String.valueOf(i)).start();
        }
        while (Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+"\t number "+demo1_test.number);
    }

    //通过是否有volatile来测试可见性
    private static void testSeeByVolatile() {
        Demo1_test myData = new Demo1_test();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"\t come in");
            try {
                TimeUnit.SECONDS.sleep(3);
            }catch (InterruptedException interruptedException){
                interruptedException.printStackTrace();
            }
            myData.add60();
            System.out.println(Thread.currentThread().getName()+"\t update number "+myData.number);
        },"testThread").start();
        while (myData.number==0)
        {
            //线程就搁这儿一直等
        }
        System.out.println(Thread.currentThread().getName()+"\t number"+myData.number);
    }


}
