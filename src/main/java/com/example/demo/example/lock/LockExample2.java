package com.example.demo.example.lock;

import com.example.demo.annoation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Administrator on 2018/11/24 0024.
 * ReentrantReadWriteLock
 * 在有读和写操作时，不会进行写操作，避免了在同一时刻既有写又有读的情况
 * 但是存在这样一个问题，当读过多时，会遭遇线程饥饿
 */
@Slf4j
@ThreadSafe
public class LockExample2 {
   private final Map<String,Data> map = new TreeMap<>();
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readlock = lock.readLock();
    private final Lock writelock = lock.writeLock();

    public Data getKeys(String string){
        readlock.lock();
        try{
            return map.get(string);
        }finally {
            readlock.unlock();
        }
    }
    public void set(String string,Data o){
        writelock.lock();
        try{
             map.put(string,o);
        }finally {
            writelock.unlock();
        }
    }
    class Data{

    }
}
