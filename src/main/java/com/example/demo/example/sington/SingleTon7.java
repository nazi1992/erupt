package com.example.demo.example.sington;

import com.example.demo.annoation.Recommend;
import com.example.demo.annoation.ThreadSafe;

/**
 * Created by Administrator on 2018/11/26 0026.
 * 利用枚举来构造单例
 */
@ThreadSafe
@Recommend//推荐这种模式，相对于懒汉模式，安全性保证，相对于饿汉模式，不会造成资源的浪费
public class SingleTon7 {
    private SingleTon7(){}//定义私有构造方法，也就是只能在本类中实例化
    //定义静态方法，创建对象

    public static SingleTon7 newInstance(){
        return Sington.sington.getInstance();
    }
    private enum Sington{
        sington;
        private   SingleTon7 singleTon7 = null;

        //JVM保证该构造方法绝对只调用一次
        private Sington(){
            singleTon7 = new SingleTon7();
        }
        public SingleTon7 getInstance(){
            return singleTon7;
        }
    }
}
