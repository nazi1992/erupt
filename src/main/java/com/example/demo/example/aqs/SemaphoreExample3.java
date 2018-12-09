package com.example.demo.example.aqs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2018/12/9 0009.
 * 并发量，访问有限的资源，比如访问数据库可用使用
 */
public class SemaphoreExample3 {
    private static int ThreadNum = 200;

    public static void main(String[] args) throws Exception{
        final Semaphore semaphore = new Semaphore(3);//并发量20
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i=0;i<ThreadNum;i++)
        {
            final int count = i;
            executorService.execute(()->{

                try {
                   if(semaphore.tryAcquire(5000, TimeUnit.MILLISECONDS)){//如果没有获取到就等待五秒，然后执行
                       test(count);

                       semaphore.release();//释放一个许可，释放
                   }

                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                }
            });
        }
        System.out.println("finish");
        executorService.shutdown();//释放资源
    }
    private static void test(int i) throws Exception{
        Thread.sleep(100);
        System.out.println("i="+i);
        //  Thread.sleep(100);
    }
}
