package com.zlp.dairy.business.dto;

import org.apache.tomcat.util.net.openssl.OpenSSLUtil;

/**
 * Created by Ygritte Zhu on 2020/12/8
 */
public enum StudentEnum {

    STUDENT("Student"),
    TEACHER("Teacher");

    private String name;

    StudentEnum(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public static void main(String[] args) {
        System.out.println(StudentEnum.STUDENT.getName());

        StudentEnum[] values = StudentEnum.values();
        for (StudentEnum value : values) {
            System.out.println("name:" +value.getName());
        }
    }
}
