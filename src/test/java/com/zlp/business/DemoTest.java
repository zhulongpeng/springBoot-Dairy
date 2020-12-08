package com.zlp.business;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Ygritte Zhu on 2020/12/8
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("dev")
@SpringBootTest(classes = DemoTest.class)
public class DemoTest {

    @Test
    public void test1(){
        System.out.println("This is a test");
    }
}
