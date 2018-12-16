package com.example.demo.example.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2018/12/16 0016.
 * newCachedThreadPool
 */
public class ThreadPoolExample1 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i=0;i<10;i++)
        {
            final int index= i;
            executorService.execute(()->{
                System.out.println("task=="+index);
            });
        }
        executorService.shutdown();
    }
}
