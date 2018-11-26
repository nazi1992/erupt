package com.example.demo.example.sington;

import com.example.demo.annoation.ThreadSafe;

/**
 * Created by Administrator on 2018/11/26 0026.
 * 饿汉式加载,是线程安全的.
 * 如果构造方法中有太多需要加载的，则可能会造成加载该类特别慢，可能会引起性能问题，只加载而不使用，则可能会造成资源浪费，1，如果该类的构造方法不会加载太多的东西，2该对象必须使用，可用使用饿汉式加载
 */
@ThreadSafe
public class SingleTon2 {
    private static SingleTon2 singleTon1 = new SingleTon2();//定义的时候创建对象
    private SingleTon2(){}//定义私有构造方法，也就是只能在本类中实例化
    //定义静态方法，创建对象

    public static SingleTon2 newInstance(){
       return singleTon1;
    }
}
