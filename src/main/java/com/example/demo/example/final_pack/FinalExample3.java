package com.example.demo.example.final_pack;


import com.example.demo.annoation.ThreadSafe;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

/**
 * Created by Administrator on 2018/11/27 0027.
 * Immutable类,线程安全的
 */
@ThreadSafe
public class FinalExample3 {
  private static ImmutableList list = ImmutableList.of(1,2,3);
  private static ImmutableSet set = ImmutableSet.copyOf(list);
  private static ImmutableMap<Integer,Integer> map = ImmutableMap.of(1,2,3,4);//第一个值是key第二个值是value，第三个值是key，第4个值是value，以此类推。
  private static ImmutableMap<Integer,Integer> map1 = ImmutableMap.<Integer,Integer>builder().put(1,2).put(3,4).build();//创建方式2

  public static void main(String[] args) {
    //list.add(3);//UnsupportedOperationException
   // set.add(3);//UnsupportedOperationException
    map1.put(1,2);//UnsupportedOperationException
  }
}
