package com.zlp.dairy.business.controller;

import com.zlp.dairy.base.util.ResResult;
import com.zlp.dairy.business.model.LanguageMO;
import com.zlp.dairy.business.model.LanguageVM;
import com.zlp.dairy.business.service.LanguageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Api(value = "language")
@RestController
public class LanguageController {

    @Autowired
    private LanguageService languageService;

    @ApiOperation("创建语言")
    @PostMapping(value = "/v1/cms/language", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResResult<String> createLanguage(
            @ApiParam(name = "language", value = "language") @RequestBody LanguageMO languageMO
    ){
        ResResult<String> result = new ResResult<>();
        try {
            result.success(languageService.createLanguage(languageMO));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @ApiOperation("查询所以语言项")
    @GetMapping("/v1/cms/language")
    @ResponseBody
    public ResResult<List<LanguageVM>> getLanguageList(){
        ResResult<List<LanguageVM>> result = new ResResult<>();
        try {
            result.success(languageService.getLanguageList());
        } catch (Exception e) {
            result.error(e.getMessage());
        }
        return result;
    }

}
