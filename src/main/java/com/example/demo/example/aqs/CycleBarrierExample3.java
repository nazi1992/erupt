package com.example.demo.example.aqs;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2018/12/9 0009.
 */
public class CycleBarrierExample3 {
    private static  CyclicBarrier cyclicBarrier =new CyclicBarrier(5,()->{
        System.out.println("-----------before--------");//保证在满足等待条件后首先执行该语句
    });

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
        executorService.shutdown();
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
            cyclicBarrier.await();

        System.out.println("continue");
    }
}
