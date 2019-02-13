package com.zlp.dairy.business.service;


import com.zlp.dairy.business.model.CategoryMO;

import java.util.List;

public interface CategoryService {
    
    String createCategory(List<CategoryMO> categoryMOList);
}
