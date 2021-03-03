package com.zlp.business;

import com.alibaba.fastjson.JSON;
import com.zlp.dairy.base.entity.Student;
import com.zlp.dairy.business.dto.Interesting;
import com.zlp.dairy.business.entity.Item;
import com.zlp.dairy.design.test.StaticTest;
import com.zlp.dairy.design.test.TargetObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import org.springframework.util.StopWatch;
import java.io.*;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * Created by Ygritte Zhu on 2020/12/8
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("dev")
@SpringBootTest(classes = DemoTest.class)
public class DemoTest {

    @Test
    public void multiThreadTest(){
        //获取Java线程管理MXBean
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        //不需要获取同步的monitor和synchonizer信息，仅获取线程和线程堆栈信息
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        //遍历线程信息，仅打印线程ID和线程名称信息
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println("[" + threadInfo.getThreadId() + "] " + threadInfo.getThreadName());
        }
    }

    @Test
    public void currentHashMapTest(){
        ConcurrentHashMap<String, String> hashMap = new ConcurrentHashMap<>();
        hashMap.put("","");
    }

    @Test
    public void classTest() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        Class<?> aClass = Class.forName("com.zlp.dairy.design.test.TargetObject");
        TargetObject targetObject = (TargetObject) aClass.newInstance();
//        targetObject.setValue("这个是value");
        System.out.println(JSON.toJSONString(targetObject));
        //获取类中所有定义的方法
        Method[] declaredMethods = aClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println("**********类中的方法***:"+declaredMethod.getName());
        }
        /**
         * 获取指定方法
         */
        Method publicMethods = aClass.getDeclaredMethod("publicMethod", String.class);
        publicMethods.invoke(targetObject,"publicMethod");
        /**
         * 获取指定参数并对参数进行修改
         */
        Field declaredFields = aClass.getDeclaredField("value");
        declaredFields.setAccessible(true);
        declaredFields.set(targetObject, "zhuzhu");
        /**
         * 调用private方法
         */
        Method privateMethod = aClass.getDeclaredMethod("privateMethod");
        //为了调用private方法取消安全检查
        privateMethod.setAccessible(true);
        privateMethod.invoke(targetObject);
    }

    @Test
    public void test0303a(){
        StaticTest staticTest = new StaticTest();
        staticTest.getClass();
    }

    @Test
    public void test0302w(){
        Integer[] myArray = {1, 2 , 3};
        List<Integer> ints = Arrays.asList(myArray);
        System.out.println(JSON.toJSONString(ints));
        ints.add(4);
        System.out.println(JSON.toJSONString(ints));
    }

    @Test
    public void test0302q(){
        String[] myArray = {"Apple","Banana","Orange"};
        List<String> stringList = Arrays.asList(myArray);
        System.out.println(JSON.toJSONString(stringList));
        System.out.println(JSON.toJSONString(Arrays.asList("Apple","Banana","Orange")));
    }

    /**
     * 关于BigDecimal
     */
    @Test
    public void test0302d(){
        BigDecimal a = new BigDecimal(1.0);
        BigDecimal b = new BigDecimal(0.9);
        BigDecimal c = new BigDecimal(0.8);
        BigDecimal x = a.subtract(b);
        BigDecimal y = b.subtract(c);
        System.out.println(x);
        System.out.println(y);
        System.out.println(Objects.equals(x, y));
        BigDecimal aa = new BigDecimal("1.0");
        BigDecimal bb = new BigDecimal("0.9");
        // " compareTo  -1表示小于，0表示等于，1表示大于"
        System.out.println("compareTo");
        System.out.println(aa.compareTo(bb));
        BigDecimal bigDecimal = new BigDecimal("0.81868188818");
        BigDecimal bigDecimal1 = bigDecimal.setScale(3, BigDecimal.ROUND_HALF_DOWN);
        System.out.println(bigDecimal1);
    }

    @Test
    public void test0302c(){
        // 浮点数之间的等值判断，基本数据类型不能用==来比较，包装数据类型不能用equals来判断
        // 因为会丢失精度
        float a = 1.0f - 0.9f;
        float b = 0.9f - 0.8f;
        System.out.println(a);
        System.out.println(b);
        System.out.println( a == b);
        System.out.println( Objects.equals(a, b));
    }

    @Test
    public void test0302b(){
        // " == "  或 equals() 方法
        // " == " 它的作用是判断两个对象的地址是不是相等。即判断两个对象是不是同一个对象（基本数据类型==比较的是值，引用数据类型==比较的是内存地址）
        Integer x = 3;
        Integer y = 3;
        System.out.println( x == y );
        Integer a = new Integer(3);
        Integer b = new Integer(3);
        System.out.println( a == b);
        System.out.println( a.equals(b) );
    }

    @Test
    public void test0302a(){
        String ss = "s";
        boolean s = Objects.equals(ss, "s");
        System.out.println(s);
    }

    @Test
    public void hashMapTest(){
        HashMap<String, String> hashMap = new HashMap<>();
        //ke不能重复，value可以重复
        hashMap.put("san","张三");
        hashMap.put("si","李四");
        hashMap.put("wu","王五");
        hashMap.put("zhang","老张");
        hashMap.put("zhang","老张啊");//老张会被覆盖
        hashMap.put("wang","老王");
        System.out.println("*******hashMap*******:"+JSON.toJSONString(hashMap));
        /**
         * 遍历HashMap
         */
        // 获取所有的key
        Set<String> keySet = hashMap.keySet();
        keySet.forEach(key->{
            System.out.println("key的值为:"+key);
        });
        //获取Map中的值
        Set<String> keySet1 = hashMap.keySet();
        keySet1.forEach(key->{
            System.out.println("map中key为 " + key + "的值为: "+hashMap.get(key));
        });
        //
        Set<Map.Entry<String, String>> entries = hashMap.entrySet();
        entries.forEach(entry->{
            System.out.println(entry.getKey() + "  =====  "+entry.getValue());
        });

        /**
         * HashMap的其他常用方法
         */
        System.out.println(" after map.size():" +hashMap.size());
        System.out.println(" after map.isEmpty(): " + hashMap.isEmpty());
        System.out.println(" remove "+hashMap.remove("si"));
        System.out.println(" after map remove:" + JSON.toJSONString(hashMap));
        System.out.println(" after map.get(wang)" +hashMap.get("wang"));
        System.out.println("after map.containsKey(wang)："+hashMap.containsKey("wang"));
        System.out.println("after containsValue(老王)："+hashMap.containsValue("老王"));
        System.out.println(" replace "+hashMap.replace("wang", "老王啊"));
        System.out.println("after map.replace(wang, 老王啊):"+JSON.toJSONString(hashMap));
    }

    @Test
    public void test0301d(){
        //创建存放int类型的linkedList
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addFirst(0);
        linkedList.add(1);
        linkedList.add(1,2);
        linkedList.addLast(3);
        System.out.println(JSON.toJSONString(linkedList));
    }

    @Test
    public void test0301c(){
        int[] a = new int[3];
        a[0] = 0;
        a[1] = 1;
        a[2] = 2;
        a[3] = 3;
        int[] ints = Arrays.copyOf(a, 10);
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]+"");
        }
    }

    @Test
    public void test0301b(){
        int[] a = new int[10];
        a[0] = 0;
        a[1] = 1;
        a[2] = 2;
        a[3] = 3;
        System.arraycopy(a, 1, a ,2 ,3);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]+ "");
        }
    }

    @Test
    public void tets0301a(){
        //存储的元素是有序的、可重复的
        List<String> list = new ArrayList<>();
        list.add("");
        //存储的元素是无序的，不可重复的
        Set<String> stringSet = new HashSet<>();
        //键值对（key-value），key是无序的，不可重复的，value是无序的，可重复的
        Map<String, String> map = new HashMap<>();
    }

    @Test
    public void test0224a(){
        List<String> list = new ArrayList<>();
        Set<String> stringSet = new HashSet<>();
        Map<String, String> map = new HashMap<>();
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        List<String> linkedList = new LinkedList<>();
        List<String> synchronizedList = Collections.synchronizedList(new LinkedList<>());
        linkedList.add("");
        linkedHashMap.put("","");
        map.put("aa","aa");
        map.put("aa","bb");
        map.get("aa");
        /**
         * final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
         *                    boolean evict) {
         *         Node<K,V>[] tab; Node<K,V> p; int n, i;
         *         if ((tab = table) == null || (n = tab.length) == 0)
         *             n = (tab = resize()).length;
         *         if ((p = tab[i = (n - 1) & hash]) == null)
         *             tab[i] = newNode(hash, key, value, null);
         *         else {
         *             Node<K,V> e; K k;
         *             if (p.hash == hash &&
         *                 ((k = p.key) == key || (key != null && key.equals(k))))
         *                 e = p;
         *             else if (p instanceof TreeNode)
         *                 e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
         *             else {
         *                 for (int binCount = 0; ; ++binCount) {
         *                     if ((e = p.next) == null) {
         *                         p.next = newNode(hash, key, value, null);
         *                         if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
         *                             treeifyBin(tab, hash);
         *                         break;
         *                     }
         *                     if (e.hash == hash &&
         *                         ((k = e.key) == key || (key != null && key.equals(k))))
         *                         break;
         *                     p = e;
         *                 }
         *             }
         *             if (e != null) { // existing mapping for key
         *                 V oldValue = e.value;
         *                 if (!onlyIfAbsent || oldValue == null)
         *                     e.value = value;
         *                 afterNodeAccess(e);
         *                 return oldValue;
         *             }
         *         }
         *         ++modCount;
         *         if (++size > threshold)
         *             resize();
         *         afterNodeInsertion(evict);
         *         return null;
         *     }
         */
        System.out.println(map.get("aa"));
        /**
         * final Node<K,V> getNode(int hash, Object key) {
         *         Node<K,V>[] tab; Node<K,V> first, e; int n; K k;
         *         if ((tab = table) != null && (n = tab.length) > 0 &&
         *             (first = tab[(n - 1) & hash]) != null) {
         *             if (first.hash == hash && // always check first node
         *                 ((k = first.key) == key || (key != null && key.equals(k))))
         *                 return first;
         *             if ((e = first.next) != null) {
         *                 if (first instanceof TreeNode)
         *                     return ((TreeNode<K,V>)first).getTreeNode(hash, key);
         *                 do {
         *                     if (e.hash == hash &&
         *                         ((k = e.key) == key || (key != null && key.equals(k))))
         *                         return e;
         *                 } while ((e = e.next) != null);
         *             }
         *         }
         *         return null;
         *     }
         */
    }

    @Test
    public void test0223d(){
        System.out.println(Thread.currentThread().getName());
        System.out.println("通过线程池创建线程");
        ExecutorService executorService = new ThreadPoolExecutor(1, 1, 60L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10));
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        });
    }

    @Test
    public void test0223c() throws Exception {
        File file = new File("file.txt");
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
        outputStreamWriter.write("我是字符流转换成字节流输出的");
        outputStreamWriter.close();
    }

    @Test
    public void test0223b(){
        List<String> stringList = new ArrayList<>();
        stringList.add("a");
        stringList.add("d");
        stringList.add("f");
        String join = StringUtils.join(stringList,";");
        System.out.println("**********************join:"+join);
    }


    @Test
    public void test0223a() throws IOException {
        String ss = "mybatis-config.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(ss);
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = build.openSession();
    }

    @Test
    public void test9c(){
        String ss = "aa";
        StringBuffer stringBuffer = new StringBuffer();
        StringBuilder stringBuilder = new StringBuilder();
    }

    @Test
    public void test9b(){
        boolean flag = "1".equals(1);
        System.out.println(flag);
    }

    /**
     * 杨辉三角
     */
    @Test
    public void test9a(){
        int row = 10;
        int[][] arr = new int[row][row];
        for(int i = 0; i < arr.length; i++) {
            for (int j = 0; j <= i; j++) {
                if(j == 0 || j == i){
                    arr[j][i] = 1;
                }else{

                }
            }
            System.out.println();
        }

    }



    @Test
    public void test8b(){
        List<String> data = new ArrayList<>();
        data.add(IntStream.rangeClosed(1,10).mapToObj(__->"a")
        .collect(Collectors.joining(""))+UUID.randomUUID().toString());
        System.out.println(JSON.toJSONString(data));
    }

    /**
     * 商品选购
     */
    public List<Item> createCart(){
        return null;
        /*return IntStream.rangeClosed(1,3)
                .mapToObj(i -> "item" + ThreadLocalRandom.current().nextInt(items.size()))
                .map(name -> items.get(name)).collect(Collectors.toList());*/
    }


    @Test
    public void test8a(){
        Interesting interesting = new Interesting();
        new Thread(()->interesting.add()).start();
        new Thread(()->interesting.compare()).start();
    }

    /**
     * 读操作
     */
    @Test
    public void test7d(){
        int LOOP_COUNT = 100000;
        //线程次数
        List<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        List<Integer> synchronizedList = Collections.synchronizedList(new ArrayList<>());
        addAllData(copyOnWriteArrayList);
        addAllData(synchronizedList);
        StopWatch stopWatch = new StopWatch();
        int count = copyOnWriteArrayList.size();
        stopWatch.start("Read:copyOnWriteArrayList");
        //循环1000000次并发从CopyOnWriteArrayList随机查询元素
        IntStream.rangeClosed(1, LOOP_COUNT).parallel().forEach(item -> copyOnWriteArrayList.get(ThreadLocalRandom.current().nextInt(count)));
        stopWatch.stop();
        stopWatch.start("Read:synchronizedList");
        //循环1000000次并发从加锁的ArrayList随机查询元素
        IntStream.range(0, LOOP_COUNT).parallel().forEach(item -> synchronizedList.get(ThreadLocalRandom.current().nextInt(count)));
        stopWatch.stop();
        log.info(stopWatch.prettyPrint());
        Map result = new HashMap();
        result.put("copyOnWriteArrayList", copyOnWriteArrayList.size());
        result.put("synchronizedList", synchronizedList.size());
        log.info("**************************result:{}", JSON.toJSONString(result));
    }


    /**
     * 写操作
     */
    @Test
    public void test7c(){
        int LOOP_COUNT = 100000;
        //线程次数
        List<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        List<Integer> synchronizedList = Collections.synchronizedList(new ArrayList<>());
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("Write:copyOnWrite");
        //循环往写数据
        IntStream.rangeClosed(1,10000).parallel().forEach(
                item -> copyOnWriteArrayList.add(ThreadLocalRandom.current().nextInt(LOOP_COUNT))
        );
        stopWatch.stop();
        stopWatch.start("Write:Synchronized");
        IntStream.rangeClosed(1,10000).parallel().forEach(
                item -> synchronizedList.add(ThreadLocalRandom.current().nextInt(LOOP_COUNT))
        );
        stopWatch.stop();
        log.info(stopWatch.prettyPrint());
        Map map = new HashMap();
        map.put("copyOnWriteArrayList",copyOnWriteArrayList.size());
        map.put("synchronizedList",synchronizedList.size());
        log.info("******************************map:{}", JSON.toJSONString(map));
    }

    private void addAllData(List<Integer> list) {
        list.addAll(IntStream.rangeClosed(1,100000).boxed().collect(Collectors.toList()));
    }


    @Test
    public void test7b() throws InterruptedException {
        int LOOP_COUNT = 100000;
        //线程次数
        int THREAD_COUNT = 10;
        //元素数量
        int ITEM_COUNT = 1000;
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("normaluse");
        Map<String, Long> normaluse = normaluse();
        stopWatch.stop();
        //校验元素数量
        Assert.isTrue(normaluse.size() == ITEM_COUNT,"normaluse size error");
        //校验累计总数
        Assert.isTrue(normaluse.entrySet().stream()
        .mapToLong(item-> item.getValue())
                .reduce(0, Long :: sum) == LOOP_COUNT, "normaluse count error");
        stopWatch.start("goodUse");
        Map<String, Long> goodUse = goodU();
        stopWatch.stop();
        Assert.isTrue(goodUse.size() == ITEM_COUNT,"goodUse size error");
        Assert.isTrue(goodUse.entrySet().stream()
                .mapToLong(item-> item.getValue())
                .reduce(0, Long :: sum) == LOOP_COUNT, "goodUse count error");
        log.info("***************************:{}",stopWatch.prettyPrint());
    }

    private Map<String, Long> goodU() throws InterruptedException {
        //循环次数
        int LOOP_COUNT = 100000;
        //线程次数
        int THREAD_COUNT = 10;
        //元素数量
        int ITEM_COUNT = 1000;
        ConcurrentHashMap<String, LongAdder> concurrentHashMap = new ConcurrentHashMap<>(ITEM_COUNT);
        ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT);
        forkJoinPool.execute(()->IntStream.rangeClosed(1, LOOP_COUNT).parallel()
                .forEach(i->{
                    String key = "item"+ ThreadLocalRandom.current().nextInt(ITEM_COUNT);
                    //利用
                    concurrentHashMap.computeIfAbsent(key, k -> new LongAdder()).increment();
                }));
        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(1, TimeUnit.HOURS);
        return concurrentHashMap.entrySet().stream()
                .collect(Collectors.toMap(
                        e -> e.getKey(),
                        e -> e.getValue().longValue()
                ));
    }

    private Map<String, Long> normaluse() throws InterruptedException {
        //循环次数
        int LOOP_COUNT = 100000;
        //线程次数
        int THREAD_COUNT = 10;
        //元素数量
        int ITEM_COUNT = 1000;
        ConcurrentHashMap<String, Long> concurrentHashMap = new ConcurrentHashMap<>(ITEM_COUNT);
        ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT);
        forkJoinPool.execute(()->IntStream.rangeClosed(1, LOOP_COUNT).parallel()
                .forEach(i->{
                    String key = "item"+ ThreadLocalRandom.current().nextInt(ITEM_COUNT);
                    synchronized (concurrentHashMap){
                        if(concurrentHashMap.containsKey(key)){
                            concurrentHashMap.put(key, concurrentHashMap.get(key)+1);
                        }else{
                            concurrentHashMap.put(key, 1L);
                        }
                    }
                }));
        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(1, TimeUnit.HOURS);
        return concurrentHashMap;
    }

    @Test
    public void test7a() throws InterruptedException {
        //循环次数
        int LOOP_COUNT = 100000;
        //线程次数
        int THREAD_COUNT = 10;
        //元素数量
        int ITEM_COUNT = 1000;
        ConcurrentHashMap<String, LongAdder> concurrentHashMap = new ConcurrentHashMap<>(ITEM_COUNT);
        ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT);
        forkJoinPool.execute(()->IntStream.rangeClosed(1, LOOP_COUNT).parallel()
                .forEach(i->{
                    String key = "item"+ ThreadLocalRandom.current().nextInt(ITEM_COUNT);
                    //利用
                    concurrentHashMap.computeIfAbsent(key, k -> new LongAdder()).increment();
                }));
        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(1, TimeUnit.HOURS);
        Map<String, Long> collect = concurrentHashMap.entrySet().stream()
                .collect(Collectors.toMap(
                        e -> e.getKey(),
                        e -> e.getValue().longValue()
                ));
        log.info("*****************************collect:{}", JSON.toJSONString(collect));
    }


    /**
     * Map来统计Key出现次数的场景
     */
    @Test
    public void test7() throws InterruptedException {
        //循环次数
        int LOOP_COUNT = 100000;
        //线程次数
        int THREAD_COUNT = 10;
        //元素数量
        int ITEM_COUNT = 1000;
        ConcurrentHashMap<String, Long> concurrentHashMap = new ConcurrentHashMap<>(ITEM_COUNT);
        ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT);
        forkJoinPool.execute(()->IntStream.rangeClosed(1, LOOP_COUNT).parallel()
        .forEach(i->{
            log.info("*************iii*****************i:{}",i);
            String key = "item"+ ThreadLocalRandom.current().nextInt(ITEM_COUNT);
            synchronized (concurrentHashMap){
                if(concurrentHashMap.containsKey(key)){
                    concurrentHashMap.put(key, concurrentHashMap.get(key)+1);
                }else{
                    concurrentHashMap.put(key, 1L);
                }
            }
        }));
        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(1, TimeUnit.HOURS);
        log.info("****************concurrentHashMap:{}",JSON.toJSONString(concurrentHashMap));
        log.info("****************concurrentHashMapSize:{}",concurrentHashMap.size());
    }


    /***
     * 比如下面这个场景。有一个含900个元素的Map，现在再补充100个元素进去，
     * 这个补充操作由10个线程并发进行。开发人员误以为使用了ConcurrentHashMap就不会有线程安全问题，
     * 于是不加思索地写出了下面的代码：在每一个线程的代码逻辑中先通过size方法拿到当前元素数量，
     * 计算ConcurrentHashMap目前还需要补充多少元素，并在日志中输出了这个值，
     * 然后通过putAll方法把缺少的元素添加进去。
     * @throws InterruptedException
     */
    @Test
    public void test6() throws InterruptedException {
        //初始化900个元素
        ConcurrentHashMap<String, Long> concurrentHashMap = getData(900);
        ForkJoinPool forkJoinPool = new ForkJoinPool(10);
        forkJoinPool.execute(()-> IntStream.rangeClosed(1,10).parallel()
                .forEach(i->{
                    //复合逻辑需要锁一下这个ConcurrentMap
                    synchronized (concurrentHashMap) {
                        //查询还要多少元素
                        int gap = 1000 - concurrentHashMap.size();
                        log.info("gap size:{}", gap);
                        //补充元素
                        concurrentHashMap.putAll(getData(gap));
                    }
                }));
        //等待所有的元素完成
        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(1, TimeUnit.HOURS);
        log.info("finish size:{}", concurrentHashMap.size());
    }

    private ConcurrentHashMap<String, Long> getData(int count) {
        return LongStream.rangeClosed(1, count)
                .boxed()
                .collect(Collectors.toConcurrentMap(i -> UUID.randomUUID().toString(), Function.identity(),
                        (o1, o2) -> o1, ConcurrentHashMap::new));
    }


    @Test
    public void test4(){
        float f = 1.6f;
        int s = (int) f;
        System.out.println(s);
        int ceil = (int)Math.ceil((double) f);
        System.out.println(ceil);
    }

    @Test
    public void test3(){
        String s = new String("www.baidu.com");
        String s1 = new String("WWW.BAIDU.COM");
        System.out.println("规范表示");
        System.out.println(s.intern());
        System.out.println("规范表示");
        System.out.println(s1.intern());
    }

    @Test
    public void test2(){
        List<Student> studentList = new ArrayList<>();
        Student student = new Student();
        student.setId("000");
        student.setAge(11);
        student.setName("000");
        Student student1 = new Student();
        student1.setId("111");
        student1.setAge(11);
        student1.setName("111");
        Student student2 = new Student();
        student2.setId("222");
        student2.setAge(22);
        student2.setName("222");
        Student student3 = new Student();
        student3.setId("333");
        student3.setAge(33);
        student3.setName("333");
        studentList.add(student);
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);
        List<String> ss = new ArrayList<>();
        ss.add("222");
        ss.add("111");
        ss.add("333");
        List<Student> collect = studentList.stream().filter(s -> ss.contains(s.getId())).collect(Collectors.toList());
        System.out.println("********collect******"+ JSON.toJSONString(collect));
    }

    @Test
    public void test1(){

        String ss = "201811061193,201811061324,201812210003, 201812210004, 201812210005, 201812210006, 201812210007, 201812210008, 201812210009, 201812210010, 201812210011, 201812210012, 201812210013, 201812210014, 201812210015, 201812210016, 201812210017, 201812210018, 201812210019, 201812210020, 201812210021, 201812210022, 201812210023, 201812210024, 201812210025, 201812210026, 201812210027, 201812210028, 201812210029, 201812210030, 201812210031, 201812210032, 201812210033, 201812210034, 201812210035, 201812210036, 201812210037, 201812210038, 201812210039, 201812210040, 201812210041, 201812210042, 201812210043, 201812210044, 201812210045, 201812210046, 201812210047, 201812210048, 201812210049, 201812210050, 201812210051, 201812210052, 201812210053, 201812210054, 201812210055, 201812210056, 201812210057, 201812210058, 201812210059, 201812210060, 201812210061, 201812210062, 201812210063, 201812210064, 201812210065, 201812210066, 201812210067, 201812210068, 201812210069, 201812210070, 201812210071, 201812210072, 201812210073, 201812210074, 201812210075, 201812210076, 201812210077, 201812210078, 201812210079, 201812210080, 201812210081, 201812210082, 201812210083, 201812210084, 201812210086, 201812210087, 201812210088, 201812210089, 201812210090, 201812280002, 201901290048, 201902180029, 201903250001, 201903250002, 201903250003, 201903250004, 201903250005, 201903250006, 201903250007, 201903250008, 201903250009, 201903250010, 201903250011, 201903250012, 201903250013, 201903250014, 201903250015, 201903250016, 201903250017, 201903250018, 201903250019, 201903250020, 201906100002, 201907230003, 201907230004, 201907260001, 201908140004, 201910110002, 201910110003, 201912190001, 202003090015, 202009240570, 202012040001, 202012040003, 202012040004, 202012060005, 202012080001";
        List<String> stringList = Arrays.asList(String.valueOf(ss.split(",")).trim());
        System.out.println(JSON.toJSONString(stringList));
        System.out.println("This is a test");
    }
}
