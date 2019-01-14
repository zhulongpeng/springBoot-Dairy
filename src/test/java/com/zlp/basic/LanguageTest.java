package com.zlp.basic;

import com.alibaba.fastjson.JSON;
import com.zlp.dairy.base.constant.LanguageEnum;
import com.zlp.dairy.business.entity.Language;

import java.util.List;

public class LanguageTest {

    public static void main(String[] args) {
        List<Language> list = LanguageEnum.getList();
        System.out.println(JSON.toJSON(list));
        LanguageEnum en = LanguageEnum.getLanguageByCode("in");
        System.out.println(en);
        String s = "EN-sg";
        String s1 = s.toUpperCase();
        System.out.println(s1);
    }

}
