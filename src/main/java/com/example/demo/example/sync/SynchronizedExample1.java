package com.example.demo.example.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2018/11/25 0025.
 * synchronized修饰一个方法与修饰代码块
 */
public class SynchronizedExample1 {
    public static void main(String[] args) {
        SynchronizedExample1 synchronizedExample1 = new SynchronizedExample1();
        SynchronizedExample1 synchronizedExample2 = new SynchronizedExample1();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(()->{//启动两个线程运行该方法,不同对象调用相同的方法，会交替执行，不同的调用对象相互不影响。synchronized 修饰的test1 与 test2 方法作用相同，都作用于一个对象（)
            synchronizedExample1.test1(1);
        });executorService.execute(()->{
            synchronizedExample2.test1(2);

        });
    }
    public void test1(int j){
        synchronized (this)
        {//修饰代码块，作用于调用对象
            for(int i=0;i<10;i++)
            {
                System.out.println("test1====j==="+j+"=i="+i);
            }
        }
    }
    //修饰一个方法
    public synchronized  void test2(){
        for(int i=0;i<10;i++)
        {
            System.out.print("test2="+i);
        }
    }
}
