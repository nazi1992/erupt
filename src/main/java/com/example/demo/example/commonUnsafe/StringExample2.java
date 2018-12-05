package com.example.demo.example.commonUnsafe;

import com.example.demo.annoation.ThreadSafe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by Administrator on 2018/12/5 0005.
 * 线程安全 stringBuffer
 */
@ThreadSafe
public class StringExample2 {
    public static int clientTotal =5000;//线程数
    public static int threadTotal = 200;//并发的线程数
    public static StringBuffer stringBuffer  = new StringBuffer("");
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();//创建线程池
        final Semaphore semaphore = new Semaphore(threadTotal);//定义信息量，即并发数
        for(int i=0;i<clientTotal;i++)
        {
            executorService.execute(()->{
                try {
                    semaphore.acquire();//判断当前进程是否运行被执行，如果达到了一定的并发数，add会被临时阻塞掉，当acquire能够返回值时，当前add才能执行
                    update();
                    semaphore.release();//释放当前进程
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            });

        }
        try{
            executorService.shutdown();//关闭线程池;
        }catch (Exception e)
        {

        }
        System.out.println("string_length:{"+stringBuffer.length()+"}");
    }
    private static void update(){
        stringBuffer.append("l");
    }
}
