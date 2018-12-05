package com.example.demo.example.commonUnsafe;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by Administrator on 2018/12/5 0005.
 * 运行结果  出现多行异常  SimpleDateFormat 不是一个线程安全的对象,可用采用利用jvm的线程封闭，将该对象写在方法内,每次声明一个新的对象来使用
 */
public class DateFormatExample1 {
   // private static SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("yyyymmdd");//运行结果  出现多行异常java.lang.NumberFormatException: multiple points
    public static int clientTotal =5000;//线程数
    public static int threadTotal = 200;//并发的线程数
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();//创建线程池
        final Semaphore semaphore = new Semaphore(threadTotal);//定义信息量，即并发数
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for(int i=0;i<clientTotal;i++)
        {
            executorService.execute(()->{
                try {
                    semaphore.acquire();//判断当前进程是否运行被执行，如果达到了一定的并发数，add会被临时阻塞掉，当acquire能够返回值时，当前add才能执行
                    format();
                    semaphore.release();//释放当前进程
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
                countDownLatch.countDown();//每执行完一次就减1
            });

        }
        try{
            countDownLatch.await();//能够保证所有的进程执行完，并且countDown的值减为0
            executorService.shutdown();//关闭线程池;
        }catch (Exception e)
        {

        }
    }
    private static void format()
    {
        try {
           SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyymmdd");//可用采用利用jvm的线程封闭，将该对象写在方法内,每次声明一个新的对象来使用
            simpleDateFormat.parse("20180311");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
