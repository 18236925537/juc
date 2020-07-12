package com.zpb.juc.atomic;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author zhoupengbing
 * @packageName com.zpb.juc.atomic
 * @email zhoupengbing@telecomyt.com.cn
 * @description 三种方式性能比较
 * @createTime 2020年06月26日 18:53:00
 * @Version v1.0
 */
public class SyncVsAtomicVsLongAdder {

    static long       count1 = 0L;
    static AtomicLong count2 = new AtomicLong(0L);
    static LongAdder  count3 = new LongAdder();

    public static void main(String[] args) throws Exception{

        Thread [] threads = new Thread[1000];

        //悲观锁方式
        Object object = new Object();
        for (int i = 0; i < threads.length; i++) {
             threads[i] =
                new Thread(new Runnable(){
                    @Override
                    public void run() {
                        for (int j = 0; j < 100000; j++) {
                            synchronized (object){
                                count1 ++;
                            }
                        }
                    }
                });
        }
        long start = System.currentTimeMillis();
        for (Thread thread:threads) thread.start();
        for (Thread thread:threads) thread.join();
        long end = System.currentTimeMillis();
        System.out.println("Sync " + count1 + " 耗时 "+ (end - start) +" ms");

        //AtomicLong 方式
        for (int i = 0; i < threads.length; i++) {
            threads[i] =
                    new Thread(() -> {
                        for (int j = 0; j < 100000; j++)
                            count2.incrementAndGet();
                    });
        }
        start = System.currentTimeMillis();
        for (Thread thread:threads) thread.start();
        for (Thread thread:threads) thread.join();
        end = System.currentTimeMillis();
        System.out.println("Atomic " + count2.get() + " 耗时 "+ (end - start) +" ms");

        //LongAdder 方式
        for (int i = 0; i < threads.length; i++) {
            threads[i] =
                    new Thread(() ->{
                        for (int j = 0; j < 10000; j++)
                            count3.increment();
                    });
        }
        start = System.currentTimeMillis();
        for (Thread thread:threads) thread.start();
        for (Thread thread:threads) thread.join();
        end = System.currentTimeMillis();
        System.out.println("LongAdder " + count3.longValue() + " 耗时 "+ (end - start) +" ms");

    }

}
