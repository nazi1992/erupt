package com.example.demo.example.publics;

import com.example.demo.annoation.NotThreadSafe;

/**
 * Created by Administrator on 2018/11/25 0025.
 * 对象逸出
 * 在对象未完成构造之前，不可以将其发布.
 */
@NotThreadSafe
public class UnsafePublicExample2 {
    private int thiscanbeEscape = 0;
    public UnsafePublicExample2(){
        new InnerClass();
    }
    public class InnerClass{
        public void InnerMethod(){
            //实际上是因为内部类构造的时候，会把外部类的对象this隐式的作为一个参数传递给内部类的构造方法,在发布的时候，执行构造方法未完成时，this逸出，this被所有线程共享
            try {
                Thread.sleep(1000); // 调用sleep模拟其他耗时的初始化操作
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println(UnsafePublicExample2.this.thiscanbeEscape);
        }
    }

    public static void main(String[] args) {
        for(int i=0;i<10;i++)
        {
            new Thread(()->{
                //System.out.println( new UnsafePublicExample2().new InnerClass().InnerMethod());
                new UnsafePublicExample2().new InnerClass().InnerMethod();

            }).start();
        }


    }

}
