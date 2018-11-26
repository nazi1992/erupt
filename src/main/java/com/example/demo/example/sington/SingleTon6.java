package com.example.demo.example.sington;

import com.example.demo.annoation.ThreadSafe;

/**
 * Created by Administrator on 2018/11/26 0026.
 * 通过static 静态块类加载
 *
 */
@ThreadSafe
public class SingleTon6 {
    private static SingleTon6 singleTon1 = null;//该变量定义必须在static前面，因为必须定义singleton。,静态块和静态变量是顺序执行的

    static {
        singleTon1 = new SingleTon6();
    }

    private SingleTon6(){}//定义私有构造方法，也就是只能在本类中实例化
    //定义静态方法，创建对象

    public static SingleTon6 newInstance(){
       return singleTon1;
    }

    public static void main(String[] args) {
        System.out.println(singleTon1);
    }
}
