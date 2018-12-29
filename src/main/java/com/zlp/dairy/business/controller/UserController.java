package com.zlp.dairy.business.controller;

import com.zlp.dairy.base.util.ResResult;
import com.zlp.dairy.base.util.XaUtil;
import com.zlp.dairy.business.entity.User;
import com.zlp.dairy.business.model.UserMO;
import com.zlp.dairy.business.model.UserVO;
import com.zlp.dairy.business.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("login")
    @PostMapping("/v1/cms/login")
    @ResponseBody
    public ResResult<UserVO> login(
            @ApiParam(name = "userName", value = "用户名") @RequestParam String userName,
            @ApiParam(name = "password", value = "密码") @RequestParam String password
    ){
        ResResult<UserVO> result = new ResResult<>();
        try {
            result.success(userService.login(userName, password));
        } catch (Exception e) {
            result.error(e.getMessage());
        }
        return result;
    }

    @ApiOperation("创建user")
    @PostMapping(value = "/v1/cms/user", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResResult<String> createUser(
            @ApiParam(name = "userMO", value = "用户入参对象")@RequestBody UserMO userMO
    ){
        ResResult<String> result = new ResResult<>();
        try {
            result.success(userService.createUser(userMO));
        } catch (Exception e) {
            result.error(e.getMessage());
        }
        return result;
    }

    @ApiOperation("修改User")
    @PutMapping(value = "/v1/cms/user", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResResult<String> updateUser(
            @ApiParam(name = "userId", value = "userId") @RequestParam String userId,
            @ApiParam(name = "userMO", value = "入参对象") @RequestBody UserMO userMO
    ){
        ResResult<String> result = new ResResult<>();
        try {
            User user = userService.findUserByUserId(userId);
            if(XaUtil.isEmpty(user)) return null;
            result.success(userService.updateUser(user, userMO));
        } catch (Exception e) {
            result.error(e.getMessage());
        }
        return result;
    }

    @ApiOperation("删除User")
    @DeleteMapping(value = "/v1/cms/user/{userId}")
    @ResponseBody
    public ResResult<String> deleteUser(
            @ApiParam(name = "userId", value = "userId") @PathVariable String userId
    ){
        ResResult<String> result = new ResResult<>();
        try {
            result.success(userService.deleteByUserId(userId));
        } catch (Exception e) {
            result.error(e.getMessage());
        }
        return result;
    }

    @ApiOperation("根据userId查找User")
    @GetMapping("/v1/cms/user/{userId}")
    @ResponseBody
    public ResResult<UserVO> getUserByUserId(
            @ApiParam(name = "userId", value = "userId") @PathVariable String userId
    ){
        ResResult<UserVO> result = new ResResult<>();
        try {
            result.success(userService.getUserByUserId(userId));
        } catch (Exception e) {
            result.error(e.getMessage());
        }
        return result;
    }

    @ApiOperation("查询所有分页")
    @GetMapping("/v1/cms/userPage")
    @ResponseBody
    public ResResult<List<UserVO>> getUserPage(
            @ApiParam(name = "searchStr", value = "searchStr") @RequestParam(required = false) String searchStr,
            @ApiParam(name = "nextPage", value = "nextPage") @RequestParam(defaultValue = "0",required = false) Integer nextPage,
            @ApiParam(name = "pageSize", value = "pageSize") @RequestParam(defaultValue = "10",required = false) Integer pageSize
    ){
        ResResult<List<UserVO>> result = new ResResult<>();
        try {
            Page<UserVO> userList = userService.getUserByFilter(searchStr, nextPage, pageSize);
            result.success(userList.getContent());
            result.setCount((int) userList.getTotalElements());
        } catch (Exception e) {
            result.error(e.getMessage());
        }
        return result;
    }

    @ApiOperation("查询所有不分页")
    @GetMapping("/v1/cms/userList")
    @ResponseBody
    public ResResult<List<UserVO>> getUserList(){
        ResResult<List<UserVO>> result = new ResResult<>();
        try {
            result.success(userService.getUser());
        } catch (Exception e) {
            result.error(e.getMessage());
        }
        return result;
    }
}
