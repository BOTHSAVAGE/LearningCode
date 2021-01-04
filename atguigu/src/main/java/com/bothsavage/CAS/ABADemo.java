package com.bothsavage.CAS;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABADemo {

    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100,1);

    public static void main(String[] args) {
        //演示ABA问题
//        TestABA();
        //解决ABA问题
        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"第一次版本号\t"+stamp);
            //暂停一秒
            try{TimeUnit.SECONDS.sleep(1);}catch(InterruptedException e){e.printStackTrace();}
            atomicStampedReference.compareAndSet(100,101,stamp,stamp+1);
            stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"第二次版本号\t"+stamp);
            atomicStampedReference.compareAndSet(101,100,stamp,stamp+1);
            System.out.println(Thread.currentThread().getName() + "第三次版本号\t" + atomicStampedReference.getStamp());
        },"t3").start();

        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"第一次版本号\t"+stamp);
            //暂停3秒
            try{TimeUnit.SECONDS.sleep(3);}catch(InterruptedException e){e.printStackTrace();}
            boolean result = atomicStampedReference.compareAndSet(100, 2019, stamp, stamp + 1);
            System.out.println(Thread.currentThread().getName()+"\t"+result+"最新版本号\t"+atomicStampedReference.getStamp());
            System.out.println(Thread.currentThread().getName() + "\t当前实际最新value\t" + atomicStampedReference.getReference());
        },"t4").start();

    }

    private static void TestABA() {
        new Thread(()->{
            atomicReference.compareAndSet(100,101);
            atomicReference.compareAndSet(101,100);
        },"t1").start();
        new Thread(()->{
            //暂停一会儿线程
            try{ TimeUnit.SECONDS.sleep(10);}catch(InterruptedException e){e.printStackTrace();}
            System.out.println(atomicReference.compareAndSet(100, 2019)+"\t"+atomicReference.get().toString());
            System.out.println(Thread.currentThread().getName());
        }).start();
    }
}
