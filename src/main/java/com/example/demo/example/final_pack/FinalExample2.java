package com.example.demo.example.final_pack;



import com.example.demo.annoation.ThreadSafe;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/11/27 0027.
 * 不可变对象 unmodifiableMap  线程安全的。数据不允许修改
 */
@ThreadSafe
public class FinalExample2 {
    private final static Integer a = 1;
    private final static String b = "2";
    private  static Map<Integer,Integer> map = new HashMap<>();
    static {
        map.put(1,1);
        map.put(2,2);
        map = Collections.unmodifiableMap(map);//调用该方法也能使对象变成不可变对象
    }

    public static void main(String[] args) {

        map.put(1,3);

        System.out.println(map.get(1));//map也不能修改其中的值,会抛出异常
    }
    private void test( final int a)
    {
        //a = 1;修饰传入的变量，也是不能变化的，否则会编译报错
    }
}
