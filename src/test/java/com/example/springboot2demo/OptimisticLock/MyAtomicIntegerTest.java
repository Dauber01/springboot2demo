package com.example.springboot2demo.OptimisticLock;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * to do
 *
 * @author Lucifer
 * @date $(DATE)
 */
public class MyAtomicIntegerTest {

    @Test
    public void getFailureCount() throws Exception {
    }

    @Test
    public void testCustomizeAtomic() throws InterruptedException {
        final MyAtomicInteger myAtomicInteger = new MyAtomicInteger();
        // 执行自增和自减操作的线程各10个，每个线程操作10000次
        Thread[] incs = new Thread[10];
        Thread[] decs = new Thread[10];
        for (int i = 0; i < incs.length; i++) {
            incs[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    myAtomicInteger.inc();
                }
            });
            incs[i].start();
            decs[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    myAtomicInteger.dec();
                }
            });
            decs[i].start();
        }

        for (int i = 0; i < 10; i++) {
            incs[i].join();
            decs[i].join();
        }

        System.out.println(myAtomicInteger.get() + " with " + myAtomicInteger.getFailureCount() + " failed tries.");
    }

}