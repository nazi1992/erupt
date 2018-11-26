package com.example.demo.example.atomic;

import com.example.demo.annoation.ThreadSafe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Administrator on 2018/11/24 0024.
 * AtomicExample6_AutomicBoolean 的使用
 */
@ThreadSafe
public class AtomicExample6_AutomicBoolean {
    private static AtomicBoolean isHappend = new AtomicBoolean(false);
    private static boolean ishappend1 = false;
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
                    test();
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
        System.out.println("count:{"+isHappend.get()+"}");
        System.out.println("count--ishappend1:{"+ishappend1+"}");
    }
    private static void test(){
        if(isHappend.compareAndSet(false,true))//具有原子性,如果值为false则最后修改为true
        {
            System.out.println("excute");//该if语句只会执行一次,某个流程只执行一次可用参考

        }
         if(!ishappend1) ishappend1 = true;
    }
}
