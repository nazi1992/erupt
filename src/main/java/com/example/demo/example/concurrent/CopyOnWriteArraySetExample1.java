package com.example.demo.example.concurrent;

import com.example.demo.annoation.ThreadSafe;

import java.util.Set;
import java.util.concurrent.*;

/**
 * Created by Administrator on 2018/12/5 0005.
 * 线程安全的类 CopyOnWriteArraySet
 * 单个修改或删除是线程安全的，如果是批量操作则需要程序自己加上同步性，因为该并发容器只会使每一次修改具有原子性，而不是批量的修改。
 */
@ThreadSafe
public class CopyOnWriteArraySetExample1 {
    public static int clientTotal =5000;//线程数
    public static int threadTotal = 200;//并发的线程数
    public static Set set   = new CopyOnWriteArraySet<>();//并发容器
    //public static Set set   = new ConcurrentSkipListSet<>();//并发容器，
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
