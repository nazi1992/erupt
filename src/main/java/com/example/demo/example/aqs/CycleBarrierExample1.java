package com.example.demo.example.aqs;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2018/12/9 0009.
 */
public class CycleBarrierExample1 {
    private static  CyclicBarrier cyclicBarrier =new CyclicBarrier(5);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i <10 ; i++) {
            final int countNum = i;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            executorService.execute(()->{
                try {
                    race(countNum);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    /**
     * 0is already
     1is already
     2is already
     3is already
     4is already
     continue
     continue
     continue
     continue
     continue
     5is already
     6is already
     7is already
     8is already
     9is already
     continue
     continue
     continue
     continue
     continue
     *
     *
     */


    private static void race(int i) throws Exception
    {
        Thread.sleep(1000);
        System.out.println(i+"is already");
        cyclicBarrier.await();//当达到5个线程都执行之后，再执行下面的程序
        System.out.println("continue");
    }
}
