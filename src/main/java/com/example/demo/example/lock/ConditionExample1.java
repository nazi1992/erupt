package com.example.demo.example.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2018/12/9 0009.
 * condition用于线程间通信
 */
public class ConditionExample1 {
    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();

        new Thread(()->{

            try {
                reentrantLock.lock();//
                System.out.println("获得锁--one");
                condition.await();//使线程进入等待队列
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                reentrantLock.unlock();

            }
            System.out.println("释放锁--one");

        }).start();
        new Thread(()->{

            try {
                reentrantLock.lock();//
                System.out.println("获得锁--two");
               condition.signalAll();//唤醒线程1
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                reentrantLock.unlock();

            }
            System.out.println("释放锁--two");

        }).start();
    }
}
