package com.example.demo.example.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2018/11/25 0025.
 * * synchronized修饰一个静态方法与修饰一个类

 */
public class SynchronizedExample2 {
    public static void main(String[] args) {
        SynchronizedExample2 synchronizedExample1 = new SynchronizedExample2();
        SynchronizedExample2 synchronizedExample2 = new SynchronizedExample2();
        ExecutorService executorService = Executors.newCachedThreadPool();
//        executorService.execute(()->{//启动两个线程运行该方法,不同对象调用相同的方法,会顺序执行，因为静态方法的该关键字作用于所有的对象。
//            synchronizedExample1.test2(1);
//        });executorService.execute(()->{
//            synchronizedExample2.test2(2);
//
//        });
        executorService.execute(()->{//启动两个线程运行该方法,不同对象调用相同的方法,会顺序执行，因为静态方法的该关键字作用于所有的对象。
            synchronizedExample1.test1(1);
        });executorService.execute(()->{
            synchronizedExample2.test1(2);

        });
    }

        public void test1(int j)
        {
            synchronized (SynchronizedExample2.class)
            {//synchronized修饰一个类，它的作用跟修饰一个静态方法是一致的，就是作用于所有的对象
                for(int i=0;i<10;i++)
                {
                    System.out.println("test1====j==="+j+"=i="+i);
                }
            }

        }



    //修饰一个静态方法
    public static  synchronized  void test2(int j){
        for(int i=0;i<10;i++)
        {
            System.out.println("test1====j==="+j+"=i="+i);
        }
    }
}
