package com.zlp.dairy.business.controller;


import com.zlp.dairy.base.controller.BaseController;
import com.zlp.dairy.base.util.ResResult;
import com.zlp.dairy.business.model.CountryMO;
import com.zlp.dairy.business.service.CountryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "country")
@RestController
public class CountryController extends BaseController {

    @Autowired
    private CountryService countryService;

    @ApiOperation("添加country信息")
    @PostMapping(value = "/v1/cms/country", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResResult<String> createCountry(
            @ApiParam(name = "countryMO", value = "countryMO传入对象，language不用传") @RequestBody CountryMO countryMO
            ){
        ResResult<String> result = new ResResult<>();
        try {
            result.success(countryService.createCountry(countryMO));
        } catch (Exception e) {
            result.error(e.getMessage());
        }
        return result;
    }





}
