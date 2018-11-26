package com.example.demo.example.publics;

import com.example.demo.annoation.NotThreadSafe;

import java.util.Arrays;

/**
 * Created by Administrator on 2018/11/25 0025.
    安全发布对象,
 */
@NotThreadSafe

public class UnsafePublicExample1 {
    private String[] str = {"a","b","c"};
    public String[] getStr(){
        return str;
    }

    public static void main(String[] args) {
        UnsafePublicExample1 unsafePublicExample1 = new UnsafePublicExample1();
        System.out.println(Arrays.asList(unsafePublicExample1.getStr()));
        unsafePublicExample1.getStr()[0] = "c";//线程不安全，因为域外的其他线程可能会修改域内的值，从而使读取到的值时更改过的。
        System.out.println(Arrays.asList(unsafePublicExample1.getStr()));
    }
}
