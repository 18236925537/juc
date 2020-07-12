package com.zpb.juc.atomic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhoupengbing
 * @packageName com.zpb.juc.atomic
 * @email zhoupengbing@telecomyt.com.cn
 * @description AtomicXXX用例
 * @createTime 2020年06月26日 18:51:00
 * @Version v1.0
 */
public class AtomicIntegerDemo {

    AtomicInteger count = new AtomicInteger(0);

    void testM(){
        for (int i = 0; i < 10000 ; i++) {
            //等同于count++;
            //内部使用cas操作
            count.incrementAndGet();
        }
    }

    public static void main(String[] args) {
        AtomicIntegerDemo demo = new AtomicIntegerDemo();
        List<Thread>threads = new ArrayList<>();
        //添加多个线程
        for (int i = 0; i < 10; i++) {
           threads.add(new Thread(demo::testM,"thread-"+1));
        }
        threads.forEach((o)->o.start());
        threads.forEach((o)-> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(demo.count);
    }

}
