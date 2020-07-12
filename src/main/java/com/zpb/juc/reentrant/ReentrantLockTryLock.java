package com.zpb.juc.reentrant;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhoupengbing
 * @packageName com.zpb.juc.reentrant
 * @email zhoupengbing@telecomyt.com.cn
 * @description 尝试锁
 * @createTime 2020年07月11日 11:07:00
 * @Version v1.0
 */
public class ReentrantLockTryLock {

    /**
     * 定义锁对象
     */
    Lock lock = new ReentrantLock();


    void m1(){
        try {
            lock.lock();
            for (int i = 0; i < 4; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(i);
            }
            System.out.println("m1 .... ");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 使用tryLock进行尝试锁定，不管锁定与否，方法都会继续执行
     * 可以根据tryLock的返回值来判定是否锁定
     * 也可以设置tryLock的锁定时间
     */
    void m2(){
        boolean locked = false;
        try {
            //尝试5秒钟得到锁
            locked = lock.tryLock(5,TimeUnit.SECONDS);
            if(locked){
                System.out.println("m2 ... " + locked);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(locked){
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ReentrantLockTryLock tryLock = new ReentrantLockTryLock();
        new Thread(tryLock::m1).start();
        new Thread(tryLock::m2).start();
    }

}
