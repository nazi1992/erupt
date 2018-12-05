package com.example.demo.example.threadlocal;

/**
 * Created by Administrator on 2018/12/4 0004.
 * 线程封闭---h,ThreadLocal做到了线程封闭
 */
public class ThreadLocalExample {
    private final static ThreadLocal<Long> requestHolder = new ThreadLocal();

    public  static void set(Long id)
    {
        requestHolder.set(id);//key值代表一个线程，而value值则是我们放入的long值，请求进入到后端处理器，存入ThreadLocal
    }
    public static Long getId(){//实际处理的时候，获取
        return requestHolder.get();
    } public static void remove(){
        requestHolder.remove();//移除数据，释放掉资源，不然会一直到项目重新启动才会释放掉
    }
    public static void main(String[] args) {

    }
}
