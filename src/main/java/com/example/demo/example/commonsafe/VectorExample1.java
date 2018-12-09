package com.example.demo.example.commonsafe;

import java.util.Iterator;
import java.util.Vector;

/**
 * Created by Administrator on 2018/12/6 0006.
 * test1与test2在遍历的过程中，最好不要做修改操作
 * 在多线程的情况下，一个线程对容器进行获取一个对容器进行修改操作，容易出现并发问题。
 */
public class VectorExample1 {
    private static Vector<Integer>  vector = new Vector<Integer>();
    private static void test1(){
        for (Integer i: vector
             ) {
            if(i==3)
                vector.remove(i);
        }
    }
    private static void test2(){
        for(int i=0;i<vector.size();i++)
        {
            if(vector.get(i)==3)
            {
                vector.remove(i);
            }
        }
    }
    private static void test3(){
        Iterator<Integer> iterator = vector.iterator();
        while(iterator.hasNext())
        {
            Integer i = iterator.next();
            if(i==3) vector.remove(i);
        }
    }

    public static void main(String[] args) {
        vector.add(1);vector.add(2);vector.add(3);
       // test1();// java.util.ConcurrentModificationException
        test2();//sucess
       // test3();// java.util.ConcurrentModificationException
    }
}
