package com.bothsavage.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
class Phone implements Runnable{
    public synchronized void sendSMS()throws Exception{
        System.out.println(Thread.currentThread().getId()+"\t invoke sendSMS");
        sendEmail();
    }
    public synchronized void sendEmail()throws Exception{
        System.out.println(Thread.currentThread().getId()+"\t ########### invoke sendEmail");
    }

    //==============================
    Lock lock = new ReentrantLock();
    @Override
    public void run() {
        get();
    }

    public void get(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getId()+"\t invoke get");
            set();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void set(){
        lock.lock();
        lock.lock();//只要与unlock成对存在，可以无限锁，但是必须配对，要不然死等待
        try {
            System.out.println(Thread.currentThread().getId()+"\t $$$$$$$ invoke set");

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
            lock.unlock();
        }
    }

}
public class ReentrantLockDemo {
    public static void main(String[] args) {
//        fairLock();
//        TestReenterLockSyn();
        TestReetrantLockLock();


    }

    private static void TestReetrantLockLock() {
        Thread t3 = new Thread(new Phone());
        t3.start();

        Thread t4 = new Thread(new Phone());
        t4.start();
    }

    private static void TestReenterLockSyn() {
        //说明synchronized是一个典型的可重入锁
        Phone phone = new Phone();
        new Thread(()->{
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t1").start();
        new Thread(()->{
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t2").start();
    }

    private static void fairLock() {
        //默认不公平
        Lock lock = new ReentrantLock();
        Lock lockFair = new ReentrantLock(true);
    }
}
