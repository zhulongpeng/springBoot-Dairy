package com.zlp.dairy.design.test;

/**
 * Created by Ygritte Zhu on 2021/1/25
 * 单例模式
 */
public class Singleton {

    /**
     * 分配内存
     * 初始化函数
     * 将对象指向分配内存的地址
     */

    private Singleton(){

    }

    //私有化构造函数
    private static Singleton singleton = new Singleton();

    //公共的静态方法返回一个单一实例
    public synchronized static Singleton getInstance(){
        if(singleton == null){
            synchronized (Singleton.class) {
                singleton = new Singleton();
            }
        }
        return singleton;
    }
}
