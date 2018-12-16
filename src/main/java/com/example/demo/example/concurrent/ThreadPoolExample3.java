package com.example.demo.example.concurrent;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2018/12/16 0016.
 */
public class ThreadPoolExample3 {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
//       executorService.schedule(()->{
//           System.out.println("schedule run");
//       },3, TimeUnit.SECONDS);  //每隔3秒执行
        executorService.scheduleWithFixedDelay(()->{
            System.out.println("scheduleWithfixDelay");
        },1,3,TimeUnit.SECONDS);//延迟一秒之后，每隔三秒执行任务。

       // executorService.shutdown();这个时候不适合关闭线程
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("timer-out---");
            }
        }, new Date(),5*1000);//从当前时间开始执行,每次间隔5秒继续执行  （定时器）
    }
}
