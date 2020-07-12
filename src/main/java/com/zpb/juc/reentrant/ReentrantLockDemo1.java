package com.zpb.juc.reentrant;

import java.util.concurrent.TimeUnit;

/**
 * ReentrantLock用于替代 synchronized
 * 由于m1锁定this，只有m1执行完成才执行m2
 * 使用ReentrantLock可以完成同样的功能
 * 需要注意的是，必须要手动释放锁，手动释放锁，手动释放锁
 * 使用syn锁定的话，如果遇到异常，jvm就会自动释放锁，但是lock必须手动释放锁，因此经常
 */
public class ReentrantLockDemo1 {

     synchronized void m1(){
         for (int i = 0; i < 10 ; i++) {
             try {
                 TimeUnit.SECONDS.sleep(1);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
             System.out.println("m1 " + i);
         }
     }

    synchronized void m2(){
        System.out.println("m2 开始执行 ");
    }
    
    public static void main(String[] args) {
        ReentrantLockDemo1 demo = new ReentrantLockDemo1();
        //开始执行m1
        new Thread(demo::m1).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //开始执行m2
        new Thread(demo::m2).start();
    }
    
}
