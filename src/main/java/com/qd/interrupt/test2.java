package com.qd.interrupt;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

/**
 * 打断 park
 *
 */
@Slf4j(topic = "c.test2")
public class test2 {
    public static void main(String[] args) {
        test();
    }

    private static  void test(){
        Thread t1=new Thread(()->{
           log.debug("park");
            LockSupport.park();
            log.debug("unpark....");
            log.debug("打断模式:{}",Thread.interrupted());

            LockSupport.park();
            log.debug("unpark..");
        },"t1");
        t1.start();
        try {
            Thread.sleep(1000);
            t1.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
