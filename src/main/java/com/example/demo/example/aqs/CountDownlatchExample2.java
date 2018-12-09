package com.example.demo.example.aqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2018/12/7 0007.
 */
public class CountDownlatchExample2 {
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
                    countDownLatch.countDown();//计数减1，保证必须执行，也就是每一个线程都执行完
                }
            });
        }
        countDownLatch.await(10, TimeUnit.MILLISECONDS);//等待10毫秒，就不会阻塞了,也就是在规定时间完成。，结果：先打印finish,后面在执行其他线程
        System.out.println("finish");
        executorService.shutdown();//释放资源
    }
    private static void test(int i) throws Exception{
       Thread.sleep(100);
        System.out.println("i="+i);
      //  Thread.sleep(100);
    }
}
