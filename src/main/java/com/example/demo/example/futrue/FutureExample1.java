package com.example.demo.example.futrue;

import java.util.concurrent.*;

/**
 * Created by Administrator on 2018/12/10 0010.
 */
public class FutureExample1 {
    static class myCallAble implements Callable<String>{

        @Override
        public String call() throws Exception {
            Thread.sleep(4000);
            return "done";
        }

        public static void main(String[] args) throws ExecutionException, InterruptedException {
            ExecutorService executorService = Executors.newCachedThreadPool();
            Future<String> submit = executorService.submit(new myCallAble());//得到线程计算的结果
            String s = submit.get();
            System.out.println(s);
        }
    }
}
