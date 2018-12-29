package com.zlp.dairy.business.Builder;

import com.zlp.dairy.business.entity.User;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class UserBuilder extends AbstractBuilder {

    public List<User> buildUser(){
        List<User> list = new ArrayList<>();
        String benefitId = (String) map.get("benfitId");
        String mastercardProducts = null;
        String mastercardProductType = null;
        if(map.get("mastercardProducts") != null){
           mastercardProducts = (String) map.get("mastercardProducts");
        }
        if(map.get("mastercardProductType") != null){
            mastercardProductType = (String) map.get("masetrcardProductType");
        }
        List<String> mastercardProductList = null;
        List<String> mastercardProductTypeList = null;
        if(StringUtils.isNotBlank(mastercardProducts)){
            mastercardProductList = Arrays.asList(mastercardProducts.split(";"));
        }
        if(StringUtils.isNotBlank(mastercardProductType)){
            mastercardProductTypeList = Arrays.asList(mastercardProductType.split(";"));
        }
        if(CollectionUtils.isNotEmpty(mastercardProductList)){
            mastercardProductList.forEach( temp ->{
                User user = new User();
                user.setUserId(benefitId);
                user.setSessionKey(temp);
                list.add(user);
            });
        }
        if(CollectionUtils.isNotEmpty(mastercardProductTypeList)){
            if(mastercardProductList.contains("ALL")){
                User user = new User();
                user.setUserId(benefitId);
                user.setSessionKey("ALL");
                list.add(user);
            }else{
                mastercardProductTypeList.forEach( temp -> {
                    User user = new User();
                    user.setUserId(benefitId);
                    user.setSessionKey("u");
                    list.add(user);
                });
            }
        }
        return list;
    }

    public Object getKey(String key) {
        return super.getKey(key);
    }

    public String getString(String key) {
        return super.getString(key);
    }

    public Integer getInt(String key) {
        return super.getInt(key);
    }

    public void setParam(String key, Object object) {
        super.setParam(key, object);
    }

}
