package com.zpb.juc.reentrant;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhoupengbing
 * @packageName com.zpb.juc.reentrant
 * @email zhoupengbing@telecomyt.com.cn
 * @description lock形式实现demo1
 * @createTime 2020年07月11日 10:55:00
 * @Version v1.0
 */
public class ReentrantLockDemo2 {

    /**
     * 定义锁对象
     */
    Lock lock = new ReentrantLock();

    /**
     * 方法m1
     */
    void m1() {
        try {
            lock.lock();
            for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(i);
            }
            System.out.println("m1");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void m2() {
        try {
            lock.lock();
            System.out.println("m2");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockDemo2 demo2 = new ReentrantLockDemo2();
        new Thread(demo2::m1).start();
        new Thread(demo2::m2).start();
    }


}
