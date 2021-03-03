package com.zlp.dairy.design.test;

/**
 * Created by Ygritte Zhu on 2021/3/3
 */
public class StaticTest {

    public StaticTest(){
        System.out.println("默认构造方法");
    }

    {
        System.out.println("非静态代码块");
    }

    static {
        System.out.println("静态代码块");
    }

    private static void test(){
        System.out.println("静态方法中的内容");
        {
            System.out.println("静态方法中的代码块");
        }
    }


    public static void main(String[] args) {
        StaticTest staticTest = new StaticTest();
        StaticTest.test();
        /**
         * 顺序：
         * 静态代码块---非静态代码块----默认构造方法----静态方法中的内容----静态方法中的代码块
         */
    }
}
