package com.zlp.dairy.business.controller;

import com.zlp.dairy.base.controller.BaseController;
import com.zlp.dairy.base.util.ResResult;
import com.zlp.dairy.business.model.CategoryMO;
import com.zlp.dairy.business.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

@RestController
@Api(value = "category")
public class CategoryController extends BaseController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation("添加category")
    @PostMapping("/v1/cms/login")
    @ResponseBody
    public ResResult<String> createCategory(
            @ApiParam(name = "category", value = "category")@RequestBody List<CategoryMO> categoryMOList
    ){
        ResResult<String> result = new ResResult<>();
        try {
            result.success(categoryService.createCategory(categoryMOList));
        } catch (Exception e) {
            result.error(e.getMessage());
        }
        return result;
    }
}
