package com.example.demo.example.futrue;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by Administrator on 2018/12/10 0010.
 * 可以获取线程的结果，用法等同与Ruannable,可以关注线程的运行
 */
public class futrueTaskExample1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(3000);
                return "done";
            }
        });
      new Thread(futureTask).start();
        String result =  futureTask.get();
        System.out.println(result);
    }
}
