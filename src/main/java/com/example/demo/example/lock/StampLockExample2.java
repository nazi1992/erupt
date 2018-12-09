package com.example.demo.example.lock;

import com.example.demo.annoation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.StampedLock;

/**
 * Created by Administrator on 2018/11/24 0024.
 * StampedLock  ,乐观读，在读操作远远大于写操作时，我们可用认为读写的同步执行的几率很小，所以在保证不同步的情况下，优先读操作
 */
@Slf4j
@ThreadSafe
public class StampLockExample2 {
    public static int clientTotal =5000;//线程数
    public static int threadTotal = 200;//并发的线程数
    public static int count = 0;
    private final  static StampedLock lock = new StampedLock();
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
        System.out.println("count:{"+count+"}");
    }
    private  static void add(){
      long stamp =  lock.writeLock();
        try{
            count++;
        }finally {
            lock.unlock(stamp);//释放锁

        }

    }//
}
