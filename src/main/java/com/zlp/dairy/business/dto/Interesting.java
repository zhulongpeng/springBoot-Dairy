package com.zlp.dairy.business.dto;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by Ygritte Zhu on 2021/1/11
 */
@Slf4j
public class Interesting {

    /**
     * Volatile
     * 概念：当把变量声明喂Volatile类型后，编译器与运行时都会注意到这个变量是共享的，
     * 因此不会将该变量上的操作与其他内存操作一起重排序；volatile变量不会被缓存在寄存器或者对
     * 其他处理器不可见的地方，因此在读取volatile类型的变量时会返回最新写入的值
     * 在访问volatile变量时不会执行加锁操作，因此也就不会执行线程阻塞，因此volatile变量
     * 是一种比sychronized关键字更轻量级的同步机制
     */
    volatile int a = 1;

    volatile int b = 1;

    public synchronized void add(){
        log.info("add start");
        for(int i = 0; i < 1000; i++){
            a++;
            b++;
        }
        log.info("add done");
    }

    public synchronized void compare(){
        log.info("compare start");
        for(int i = 0; i < 10000; i++){
            if(a < b){
                log.info("a:{},b:{},{}", a, b, a > b);
            }
        }
        log.info("compare done");
    }
}
