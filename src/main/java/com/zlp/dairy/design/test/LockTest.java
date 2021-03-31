package com.zlp.dairy.design.test;

import org.openjdk.jol.info.ClassLayout;

/**
 * Created by Ygritte Zhu on 2021/3/25
 */
public class LockTest {

    public static void main(String[] args) {
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        synchronized (o){
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }
}
