package com.zlp.dairy.business.controller;

import com.zlp.dairy.base.util.ResResult;
import com.zlp.dairy.business.model.LanguageMO;
import com.zlp.dairy.business.service.LanguageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "language")
@RestController
public class LanguageController {

    @Autowired
    private LanguageService languageService;

    @ApiOperation("创建语言")
    @PostMapping("/v1/cms/language")
    @ResponseBody
    public ResResult<String> createLanguage(
            @ApiParam(name = "language", value = "language")@RequestBody LanguageMO languageMO
    ){
        ResResult<String> result = new ResResult<>();
        try {
            result.success(languageService.createLanguage(languageMO));
        } catch (Exception e) {
            result.error(e.getMessage());
        }
        return result;
    }
}
