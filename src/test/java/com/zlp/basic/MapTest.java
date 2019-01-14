package com.zlp.basic;

import com.alibaba.fastjson.JSON;
import com.zlp.DairyApplicationTests;
import com.zlp.dairy.business.entity.Language;
import com.zlp.dairy.business.service.LanguageService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

public class MapTest extends DairyApplicationTests {

    @Autowired
    private LanguageService languageService;

    @Test
    public void mapTest1(){
        // map.size() 获取map集合类的大小
        Map map = new HashMap<>();
        map.put("1", "飞机");
        map.put("2", "塔克");
        map.put("3", "大炮");
        int size = map.size();
        System.out.println("map实例中的键值个数："+size);
    }

    @Test
    public void mapTest2(){
        //获取map集合类所有的值
        Map map = new HashMap();
        map.put("1", "飞机");
        map.put("2", "坦克");
        map.put("3", "大炮");
        Collection values = map.values();
        System.out.println("map实例中所有的值："+values);
        System.out.println("***************");
        values.stream().forEach(temp->{
            System.out.println(temp);
        });
    }

    @Test
    public void mapTest3(){
        //获取map集合所有的key
        Map map = new HashMap();
        map.put("1", "飞机");
        map.put("2", "坦克");
        map.put("3", "大炮");
        Set set = map.keySet();
        System.out.println("map中所有的key值为："+set);
    }

    @Test
    public void mapTest4(){
        //形参为key的名字
        Map map = new HashMap<>();
        map.put("1", "飞机");
        map.put("2", "坦克");
        map.put("3", "大炮");
        //通过key获取value
        Object value = map.get("1");
        System.out.println("得到的value值为："+value);
    }

    @Test
    public void mapTest5(){
        //根据key移除key值对应的value
        Map map = new HashMap<>();
        map.put("1", "飞机");
        map.put("2", "坦克");
        map.put("3", "大炮");
        map.remove("1");
        Collection values = map.values();
        System.out.println("移除key之后的值为："+values);
    }

    @Test
    public void mapTest6(){
        //清除map集合中所有的键值
        Map map = new HashMap<>();
        map.put("1", "飞机");
        map.put("2", "坦克");
        map.put("3", "大炮");
        map.clear();
        Collection values = map.values();
        System.out.println("此时map集合中的值为：" +values);
    }

    @Test
    public void mapTest7(){
        //Entry是Map集合中的一个内部接口，用于封装Map集合中的一组键值（key和value）
        //获取Map内部接口Entry
        Map.Entry entry;
        Map map = new HashMap<>();
        map.put("1", "飞机");
        map.put("2", "坦克");
        map.put("3", "大炮");
        //获得map中键值对的集合
        Set set = map.entrySet();
        //得到集合的迭代器
        Iterator iterator = set.iterator();
        //遍历迭代器
        while(iterator.hasNext()){
            //遍历出的键值放到entry集合里
            entry = (Map.Entry) iterator.next();
            //得到entry的key
            Object key = entry.getKey();
            //得到entry的value
            Object value = entry.getValue();
            //输出key和value
            System.out.println("key值为："+key);
            System.out.println("value的值为："+value);
        }
    }

    @Test
    public void mapTest8(){
        //map.putAll() 把一个map集合合并到另一个map集合中
        Map map = new HashMap<>();
        map.put("1", "飞机");
        map.put("2", "坦克");
        map.put("3", "大炮");
        System.out.println("map集合大大小为："+map.size());
        Map map1 = new HashMap<>();
        map1.put("4", "火箭");
        map1.put("5", "湖人");
        System.out.println("map1集合的大小为："+map1.size());
        map.putAll(map1);
        Collection values = map.values();
        System.out.println(values);
    }

    @Test
    public void mapTest9(){
        HashMap<String, String> map = new HashMap<>();
        map.put("1", "飞机");
        map.put("2", "坦克");
        map.put("3", "大炮");
        System.out.println(map);
        map.forEach((k,v)->{
            System.out.println(k + " " +v);
        });
    }

    @Test
    public void mapTest10(){
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "apple");
        map.put(2, "banana");
        map.put(3, "pear");
        Set<Map.Entry<Integer, String>> entrySet = map.entrySet();
        Iterator<Map.Entry<Integer, String>> iterator = entrySet.iterator();
        while (iterator.hasNext()){
            Map.Entry<Integer, String> next = iterator.next();
            //获取Map.Entry对象中封装的Key和value对象
            Integer key = next.getKey();
            String value = next.getValue();
            System.out.println(key+" "+value);
        }
    }

    @Test
    public void mapTest11(){
        //map集合遍历总结
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "飞机");
        map.put(2, "坦克");
        map.put(3, "大炮");
        map.put(4, "火箭");
        map.put(5, "湖人");
        map.put(6, "安安");
        //通过Map.keySet遍历key和value
        for(Integer i : map.keySet()){
            String s = map.get(i);
            System.out.println(i +""+ s);
        }
        //通过map.entrySet使用iterator遍历key和value
        Iterator<Map.Entry<Integer, String>> iterator = map.entrySet().iterator();
        System.out.println(iterator);
        while (iterator.hasNext()){
            Map.Entry<Integer, String> next = iterator.next();
            String value = next.getValue();
            Integer key = next.getKey();
            System.out.println(key + " " + value);
        }
        System.out.println("****");
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            Integer key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key +" "+value);
        }
        System.out.println("**");
        map.forEach((k,v)->{
            System.out.println(k+" "+v);
        });
    }

    @Test
    public void stringTest(){
        String s = "1,3,5,2,4,6,11,8";
        List<String> split = Arrays.asList(s.split(","));
        System.out.println(JSON.toJSONString(split));
        List<Integer> number = new ArrayList<>();
        split.stream().forEach(temp->{
            number.add(Integer.parseInt(temp));
        });
        Collections.sort(number);
        Collections.reverse(number);
        System.out.println(number);
    }

    @Test
    public void stringsTest() {
        String s = "1,12,14,15,2,8,9,45,15,48,25";
        List<String> strings = Arrays.asList(s.split(","));
        System.out.println(JSON.toJSONString(strings));
        List<Integer> number = new ArrayList<>();
        strings.stream().forEach(temp -> {
            number.add(Integer.parseInt(temp));
        });
        Collections.sort(number);
        System.out.println(number);
        Collections.reverse(number);
        System.out.println(number);
    }

    @Test
    public void sortTest(){
        String s = "sing, china, japan, test, an, bei, ci";
        List<String> strings = Arrays.asList(s.split(","));
        Collections.sort(strings);
        System.out.println(strings);
        System.out.println(JSON.toJSONString(strings));
        strings.stream().forEach(temp->{
            System.out.println(temp);
        });
    }

    @Test
    public void mapTest(){
        List<Language> allLanguage = languageService.allLanguage();
        List<String> code = allLanguage.stream().map(Language::getCode).collect(Collectors.toList());
        System.out.println(code);
        System.out.println(JSON.toJSONString(code));
        List<String> language = allLanguage.stream().map(Language::getLanguage).collect(Collectors.toList());
        System.out.println(language);
    }

    @Test
    public void groupingByTest(){
        List<Language> allLanguage = languageService.allLanguage();
        Map<String, List<Language>> collect = allLanguage.stream().collect(Collectors.groupingBy(Language::getCode));
        collect.forEach((k,v)->{
            System.out.println(k+"  "+JSON.toJSONString(v));
        });
        System.out.println("******************************************************************************************");
        Map<String, Map<String, List<Language>>> mapMap = allLanguage.stream().collect(Collectors.groupingBy(Language::getCode, Collectors.groupingBy(Language::getLanguage)));
        mapMap.forEach((k,v)->{
            System.out.println(k+"  "+JSON.toJSONString(v));
        });
    }
}
