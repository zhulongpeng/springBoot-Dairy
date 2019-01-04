package com.zlp.basic;

import com.zlp.DairyApplicationTests;
import org.junit.Test;

public class EqualsTest extends DairyApplicationTests {

    @Test
    public void equalsTest(){
        String str = new String("Hello");
        String str1 = new String("Hello");
        System.out.println(str == str1);
//        关系操作符生成的是一个boolean结果，它们计算的是操作数的值之间的比较
        System.out.println("***********");
        System.out.println(str.equals(str1));
    }
}
