package com.example.demo.example.atomic;

import com.example.demo.annoation.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Administrator on 2018/11/24 0024.
 * AutomicReference 的使用
 */
@ThreadSafe
public class AtomicExample4_AutomicReference {
    private static AtomicReference<Integer> atomicReference = new AtomicReference<Integer>(0);

    public static void main(String[] args) {
        atomicReference.compareAndSet(0,2);//2
        atomicReference.compareAndSet(0,1);//no
        atomicReference.compareAndSet(1,3);//no
        atomicReference.compareAndSet(2,4);//4
        atomicReference.compareAndSet(3,5);//no
        System.out.println(atomicReference.get());//4
    }
}
