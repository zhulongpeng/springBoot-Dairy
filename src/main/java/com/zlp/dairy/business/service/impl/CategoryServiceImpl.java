package com.zlp.dairy.business.service.impl;

import com.zlp.dairy.business.Handle.CategoryHandle;
import com.zlp.dairy.business.model.CategoryMO;
import com.zlp.dairy.business.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryHandle categoryHandle;

    @Override
    public String createCategory(List<CategoryMO> categoryMOList) {
        return null;
    }
}
