package com.example.demo.example.final_pack;



import com.example.demo.annoation.NotThreadSafe;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/11/27 0027.
 * 不可变对象 final
 */
@NotThreadSafe
public class FinalExample1 {
    private final static Integer a = 1;
    private final static String b = "2";
    private final static Map<Integer,Integer> map = new HashMap<>();
    static {
        map.put(1,1);
        map.put(2,2);
    }

    public static void main(String[] args) {
        //a = 2;基础类型不能修改
        //map = new HashMap<>();//不能指向新的对象，但是能够修改里面的值
        map.put(1,3);
        System.out.println(map.get(1));
    }
    private void test( final int a)
    {
        //a = 1;修饰传入的变量，也是不能变化的，否则会编译报错
    }
}
