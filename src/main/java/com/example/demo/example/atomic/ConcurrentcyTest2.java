package com.example.demo.example.atomic;

import com.example.demo.annoation.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.LongAdder;

/**
 * Created by Administrator on 2018/11/24 0024.
 * 线程的原子性(CAS   )，AtomicLong
 */
@Slf4j
@NotThreadSafe//线程不安全
public class ConcurrentcyTest2 {
    public static int clientTotal =5000;//线程数
    public static int threadTotal = 200;//并发的线程数
    public static LongAdder longAdder = new LongAdder();//java8新增 ,可以优先使用该类,automicLong 与 longAddr
    //高并发计数，可以优先使用
    //在线程计数很低的情况下，生成序列号等很准确全局唯一的可以使用automicLong
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();//创建线程池
        final Semaphore semaphore = new Semaphore(threadTotal);//定义信息量，即并发数
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for(int i=0;i<clientTotal;i++)//启动5000个线程
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
        System.out.println("count:{"+longAdder+"}");
    }
    private static void add(){
        longAdder.increment();
    }
}
