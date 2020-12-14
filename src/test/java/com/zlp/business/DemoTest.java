package com.zlp.business;

import com.alibaba.fastjson.JSON;
import com.zlp.dairy.base.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Ygritte Zhu on 2020/12/8
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("dev")
@SpringBootTest(classes = DemoTest.class)
public class DemoTest {

    @Test
    public void test3(){
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
