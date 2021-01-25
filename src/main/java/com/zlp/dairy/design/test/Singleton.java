package com.zlp.dairy.design.test;

/**
 * Created by Ygritte Zhu on 2021/1/25
 * 单例模式
 */
public class Singleton {

    private Singleton(){

    }

    private static Singleton singleton = new Singleton();

    public synchronized static Singleton getInstance(){
        if(singleton == null){
            synchronized (Singleton.class) {
                singleton = new Singleton();
            }
        }
        return singleton;
    }
}
