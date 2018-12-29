package com.zlp.dairy.business.Builder;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractBuilder {

    protected Map<String, Object> map = new HashMap<>();

    protected Object getKey(String key){
        return map.get(key);
    }

    protected String getString(String key){
        Object o = map.get(key);
        if(o instanceof String) return (String) o;
        return null;
    }

    protected Integer getInt(String key){
        Object o = map.get(key);
        if(o instanceof Integer) return (Integer) o;
        return null;
    }

    protected void setParam(String key, Object object){
        map.put(key, object);
    }
}
