package com.example.demo.example.sington;

import com.example.demo.annoation.ThreadSafe;

/**
 * Created by Administrator on 2018/11/26 0026.
 *
 */
@ThreadSafe
public class SingleTon5 {
    //单利对象 volatile +双重 检测机制——》禁止指令重排
    private  volatile static  SingleTon5 singleTon1 = null;//定义私有对象引用，防止外界对象访问,加上volatile防止指令重排

    private SingleTon5(){}//定义私有构造方法，也就是只能在本类中实例化
    //定义静态方法，创建对象


    public static SingleTon5 newInstance(){
        if(singleTon1 == null)//判断为null主要是为了只加载一次
        {//双重检测机制（多线程）
            synchronized (SingleTon5.class)
            {
                if(singleTon1 == null)
                {
                    singleTon1 = new SingleTon5();
                    //创建对象时相应执行的cpu的指令
                    /*
                       1，分配内存对象空间  memory = alocate()
                       2, 初始化对象
                       3，设置singleTon1指向刚分配的内存
                      对于单线程没有任何问题，但是多线程会引起指令重排，执行1,3,2等。。所以该类也时线程不安全的。
                     */
                }
            }
        }
        return singleTon1;//不为null则加载原对象
    }
}
