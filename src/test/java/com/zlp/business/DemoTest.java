package com.zlp.business;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Ygritte Zhu on 2020/12/8
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
@SpringBootTest
public class DemoTest {

    public static void main(String[] args) {
        System.out.println("test");
    }
}
