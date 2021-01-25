package com.zlp.dairy.design.test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Ygritte Zhu on 2021/1/25
 */
public class TestSingleton {

    boolean lock;

    public boolean isLock() {
        return lock;
    }

    public void setLock(boolean lock){
        this.lock = lock;
    }

    public static void main(String[] args) throws  InterruptedException{
        final Set<String> instabceSet = Collections.synchronizedSet(new HashSet<String>());
        final TestSingleton lock = new TestSingleton();
        lock.setLock(true);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i = 0 ; i < 10; i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    while(true){
                        if(!lock.isLock()){
                            Singleton singleton = Singleton.getInstance();
                            instabceSet.add(singleton.toString());
                            break;
                        }
                    }
                }
            });
        }
        Thread.sleep(5000);
        lock.setLock(false);
        Thread.sleep(5000);
        System.out.println("----------------并发情况下取到的实例");
        for(String instance : instabceSet){
            System.out.println(instance);
        }
        executorService.shutdown();
    }
}
