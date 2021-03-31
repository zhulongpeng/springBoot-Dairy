package com.zlp.dairy.design.test;

/**
 * Created by Ygritte Zhu on 2021/3/29
 */
public class TestReloadTest {

    public static void main(String[] args) {
        Integer a = 2;
        Integer b = 3;
        add(a, b);
    }

    private static int add(int i, Integer i1) {
        return i+i1;
    }

    private static Integer add(Integer a, Integer b){
        return a+b;
    }
}
