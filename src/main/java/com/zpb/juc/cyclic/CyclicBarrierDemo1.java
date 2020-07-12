package com.zpb.juc.cyclic;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 概念：
 * 场景：复杂操作[读数据库、网络、文件]，并发执行[多个线程操作]，同时到位才能继续执行其他操作
 * 参考：限流 [待补充] Guava RateLimiter
 */
public class CyclicBarrierDemo1 {

    public static void main(String[] args) {

        /**
         * 满20个就执行一次
         */
        CyclicBarrier barrier = new CyclicBarrier(20,()->{
            System.out.println("人满->发车啦");
        });

        /**
         * 执行5次
         */
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable(){
                @Override
                public void run() {
                    try {
                        barrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }
    }

}
