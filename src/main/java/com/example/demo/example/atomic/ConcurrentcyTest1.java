package com.example.demo.example.atomic;

import com.example.demo.annoation.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Administrator on 2018/11/24 0024.
 * 线程的原子性(CAS   )，使用AtomicInteger
 */
@Slf4j
@NotThreadSafe//线程不安全
public class ConcurrentcyTest1 {
    public static int clientTotal =5000;//线程数
    public static int threadTotal = 200;//并发的线程数
    public static AtomicInteger atomicInteger = new AtomicInteger(0);//源码中使用了unsafe的类，unsafe中的，某个方法，不断的比较工作内存与主内存中的值是否一致，一致才进行操作

    /**
     *   public final int getAndAddInt(Object var1, long var2, int var4) {
         int var5;
         do {
         var5 = this.getIntVolatile(var1, var2);
         } while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4));

         return var5;
         }
     * @param args
     */
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();//创建线程池
        final Semaphore semaphore = new Semaphore(threadTotal);//定义信息量，即并发数
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for(int i=0;i<clientTotal;i++)
        {
            executorService.execute(()->{
                try {
                    semaphore.acquire();//判断当前进程是否运行被执行，如果达到了一定的并发数，add会被临时阻塞掉，当acquire能够返回值时，当前add才能执行
                    add();
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
        System.out.println("count:{"+atomicInteger.get()+"}");
    }
    private static void add(){
        atomicInteger.incrementAndGet();//先加1再获取--
    }
}
