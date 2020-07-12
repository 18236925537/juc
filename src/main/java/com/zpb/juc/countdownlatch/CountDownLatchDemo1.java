package com.zpb.juc.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo1 {

   private static void usingCountLatch(){
       Thread [] threads = new Thread[100];
       CountDownLatch latch = new CountDownLatch(threads.length);
       //模拟多个线程
       for (int i = 0; i < threads.length; i++) {
           threads[i] = new Thread(() -> {
               int result = 0;
               for (int j = 0; j < 10000; j++) {
                  result += j;
                  latch.countDown();
               }
           });
       }
       
       //启动线程
       for (int i = 0; i < threads.length; i++) {
           threads[i].start();
       }

       //
       try {
           latch.await();
       } catch(Exception e) {
           e.printStackTrace();
       }

       System.out.println("latch end");
   }

   private static void usingJoin(){
       
   }

    public static void main(String[] args) {
        usingCountLatch();
    }


}
