package com.example.demo.example.sington;

import com.example.demo.annoation.NotRecommend;
import com.example.demo.annoation.ThreadSafe;

/**
 * Created by Administrator on 2018/11/26 0026.
 * 懒汉式加载,单例实例化第一次的时候创建,单线程没有任何问题
 */
@ThreadSafe
@NotRecommend//并不推荐这种写法
public class SingleTon3 {
    private static SingleTon3 singleTon1 = null;//定义私有对象引用，防止外界对象访问
    private SingleTon3(){}//定义私有构造方法，也就是只能在本类中实例化
    //定义静态方法，创建对象

    public static synchronized SingleTon3 newInstance(){//加上了synchroized关键字可用保证线程安全，但是却带来了性能问题，因为一次只能一个线程访问。
        if(singleTon1 == null)//判断为null主要是为了只加载一次
        {
            singleTon1 = new SingleTon3();
        }
        return singleTon1;//不为null则加载原对象
    }
}
