package com.zlp.dairy.design.test;

import lombok.Data;

/**
 * Created by Ygritte Zhu on 2021/3/3
 */
@Data
public class TargetObject {

    private String value;

    public TargetObject(){
        value = "JavaGuide";
    }

    public void publicMethod(String s){
        System.out.println(" I love "+ s);
    }

    private void privateMethod(){
        System.out.println(" value is " + value);
    }
}
