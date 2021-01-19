package com.zlp.dairy.business.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.lang.model.element.VariableElement;
import java.io.Serializable;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Ygritte Zhu on 2021/1/11
 */
@Data
@RequiredArgsConstructor
public class Item implements Serializable {

    //商品名
    final String name;

    //剩余库存
    int remaining = 1000;

    @ToString.Exclude
    ReentrantLock lock = new ReentrantLock();
}
