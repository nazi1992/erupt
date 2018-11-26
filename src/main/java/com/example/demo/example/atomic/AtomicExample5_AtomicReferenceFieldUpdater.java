package com.example.demo.example.atomic;

import com.example.demo.annoation.ThreadSafe;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * Created by Administrator on 2018/11/24 0024.
 * AutomicReference 的使用
 */
@ThreadSafe
public class AtomicExample5_AtomicReferenceFieldUpdater {
    private static AtomicIntegerFieldUpdater<AtomicExample5_AtomicReferenceFieldUpdater> atomicIntegerFieldUpdater = AtomicIntegerFieldUpdater.newUpdater(AtomicExample5_AtomicReferenceFieldUpdater.class,"count");
   //AtomicExample5_AtomicReferenceFieldUpdater 为要更新的对象，用本类做例子,第二个参数为需要更新类中的字段,
    // AtomicIntegerFieldUpdater   更新某一个类实例的某个字段   ,要求该字段必须由volatile字段修改，并且不能用static修饰
    private volatile int count = 100;

    public int getCount() {
        return count;
    }

    private static AtomicExample5_AtomicReferenceFieldUpdater atomicExample5_atomicReferenceFieldUpdater = new AtomicExample5_AtomicReferenceFieldUpdater();
    public static void main(String[] args) {
        if(atomicIntegerFieldUpdater.compareAndSet(atomicExample5_atomicReferenceFieldUpdater,100,150))
        {
            System.out.println("sucess  1=="+atomicExample5_atomicReferenceFieldUpdater.getCount());

        }if(atomicIntegerFieldUpdater.compareAndSet(atomicExample5_atomicReferenceFieldUpdater,100,150))
        {
            System.out.println("sucess  2=="+atomicExample5_atomicReferenceFieldUpdater.getCount());

        }else {
            System.out.println("fail=="+atomicExample5_atomicReferenceFieldUpdater.getCount());

        }
    }
}
