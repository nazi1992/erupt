package com.example.demo.example.aqs;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2018/12/9 0009.
 */
public class CycleBarrierExample2 {
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
        try{
            cyclicBarrier.await(2000, TimeUnit.MILLISECONDS);//等待两秒之后就不会在继续等待了。捕获异常后，不会影响下面线程的执行

        }catch (Exception e)
        {

        }
        System.out.println("continue");
    }
}
