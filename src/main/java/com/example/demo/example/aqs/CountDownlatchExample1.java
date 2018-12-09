package com.example.demo.example.aqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2018/12/7 0007.
 * 计数器
 */
public class CountDownlatchExample1 {
    private static int ThreadNum = 200;

    public static void main(String[] args) throws Exception{
        CountDownLatch countDownLatch = new CountDownLatch(ThreadNum);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i=0;i<ThreadNum;i++)
        {
            final int count = i;
            executorService.execute(()->{
                try {
                    test(count);
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    countDownLatch.countDown();//计数减1
                }
            });
        }
        countDownLatch.await();//能保证所有的线程执行完毕，再执行下面的finish
        System.out.println("finish");
        executorService.shutdown();//释放资源
    }
    private static void test(int i) throws Exception{
        Thread.sleep(100);
        System.out.println("i="+i);
        Thread.sleep(100);
    }
}
