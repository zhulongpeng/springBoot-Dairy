package com.zlp.dairy;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.*;
public class ArrayListTest extends DairyApplicationTests {

    @Test
    public void testArrayList(){
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
          /*  for(String item : list){
                if("2".equals(item)){
                    list.remove(item);
                }
            }
            System.out.println(list);*/
        Iterator<String> iterator = list.iterator();
        while(iterator.hasNext()){
            String item = iterator.next();
            if("2".equals(item)){
                iterator.remove();
            }
        }
        System.out.println(list);
    }

    @Test
    public void expirationDate(){
        Date dates = new Date(System.currentTimeMillis());
        System.out.println(dates);
        Date date = new Date(System.currentTimeMillis() + 2592000L * 1000);
        System.out.println(date);
    }

    @Test
    public void testAsList(){
        String[] myArray = {"Apple", "Banana", "Orange"};
        List<String> myList = Arrays.asList(myArray);
        for(String a : myList)
        System.out.println(a);
    }

    @Test
    public void testIntAsList(){
        int[] myArray = {1, 2, 3};
        List<int[]> ints = Arrays.asList(myArray);
        for(int[] a : ints){
            for(int b : a){
                System.out.println(b);
            }
        }
    }

    @Test
    public void testIntegerAsList(){
        Integer[] myArray = {1,2,3};
        List<Integer> integers = Arrays.asList(myArray);
        for(int a : integers){
            System.out.println(a);
        }
    }
//    Integer是int的包装类，int是基本数据类型
//    Integer变量必须实例化后才能使用，int变量不需要
//    Integer实际是对象的引用，指向此new的Integer对象，Int是直接存储数据值
//    Integer的默认值是null,int的默认值为0
//    由于Integer变量实际上是对一个Integer对象的引用，所以两个通过new生成的Integer变量永远是不相等的（因为new生成的是两个对象，其内存地址不同）

    @Test
    public void testInteger(){
        Integer integer = new Integer(100);
        Integer integer1 = new Integer(100);
        System.out.println(integer == integer1);
    }

    @Test
    public void testSplit(){
        String mastercardProducts = "MIP;MRG;MPG;MUS";
        if(StringUtils.isNotBlank(mastercardProducts)){
            List<String> strings = Arrays.asList(mastercardProducts.split(";"));
            for(String s : strings){
                System.out.println(s);
            }
        }
    }
}
