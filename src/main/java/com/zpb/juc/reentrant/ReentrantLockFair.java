package com.zpb.juc.reentrant;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁：所有线程都在一个等待队列里，新的线程到了之后先检查队列中是否为空，不为空，先入先出执行顺序，没有就直接执行
 * 不公平锁：synchronized默认是不公平锁
 */
public class ReentrantLockFair extends Thread{

    /**
     * 参数为true为公平锁
     */
    private static ReentrantLock lock = new ReentrantLock(true);

    /**
     * 1、通过设置fair参数来看具体的输出结果
     * 2、lock.lock();提到循环外测试结果
     */
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName()+"获得锁");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ReentrantLockFair lockFair = new ReentrantLockFair();
        Thread t1 = new Thread(lockFair);
        Thread t2 = new Thread(lockFair);
        Thread t3 = new Thread(lockFair);
        t1.start();
        t2.start();
        t3.start();
    }


}
