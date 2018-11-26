package com.example.demo.example.validate;

import com.example.demo.annoation.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by Administrator on 2018/11/24 0024.
 */
@Slf4j
@NotThreadSafe//线程不安全
public class ConcurrentcyTest {
    public static int clientTotal =5000;//线程数
    public static int threadTotal = 200;//并发的线程数
    public static volatile int count = 0;//用volatile修饰的字段值,不是线程安全的，不具有原子性。一般用作状态标识符

    /**
     * volatile bolean inited  = false;
     * //线程1
     * context = loadContext();
     * inited = true;
     * //线程2
     * while(!inited){
     *     sleep();
     * }
     * doSomethingWithConfig(context);
     * 相当于，线程1加载完之后再执行线程2，这样效率很高，即使不用synronsized
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
        System.out.println("count:{"+count+"}");
    }
    private static void add(){
        count++;
    }
}
