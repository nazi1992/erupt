package com.example.demo.example.commonsafe;

import com.example.demo.annoation.NotThreadSafe;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by Administrator on 2018/12/5 0005.
 * 线程安全的类 Collections.synchronizedSetxxx
 */
@NotThreadSafe
public class SynronizedHashSetExample1 {
    public static int clientTotal =5000;//线程数
    public static int threadTotal = 200;//并发的线程数
    public static Set set  = Collections.synchronizedSet(new HashSet<>());
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();//创建线程池
        final Semaphore semaphore = new Semaphore(threadTotal);//定义信息量，即并发数
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for(int i=0;i<clientTotal;i++)
        {
            final int count = i;
            executorService.execute(()->{
                try {
                    semaphore.acquire();//判断当前进程是否运行被执行，如果达到了一定的并发数，add会被临时阻塞掉，当acquire能够返回值时，当前add才能执行
                    update(count);
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
        System.out.println("string_length:{"+set.size()+"}");
    }
    private static void update(int i){
        set.add(i);//4987
    }
}
