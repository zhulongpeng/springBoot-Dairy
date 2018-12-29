package com.zlp.dairy.business.controller;

import com.zlp.dairy.base.util.ResResult;
import com.zlp.dairy.business.model.RoleMO;
import com.zlp.dairy.business.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "role")
@RestController
public class RoleController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RoleService roleService;

    @ApiOperation("创建Role")
    @PostMapping("/v1/cms/role")
    @ResponseBody
    public ResResult<String> createRole(
            @ApiParam(name = "roleName", value = "角色名") @RequestParam(name = "roleName") String roleName
    ){
        ResResult<String> result = new ResResult<>();
        try {
            result.success(roleService.createRole(roleName));
        } catch (Exception e) {
            result.error(e.getMessage());
        }
        return result;
    }

    @ApiOperation("根据roleId查询Role")
    @GetMapping("/v1/cms/role/{roleId}")
    @ResponseBody
    public ResResult<RoleMO> getRoleByRoleId(
            @ApiParam(name = "roleId", value = "角色ID")@PathVariable String roleId
    ){
        ResResult<RoleMO> result = new ResResult<>();
        try {
            result.success(roleService.getRoleByRoleId(roleId));
        } catch (Exception e) {
            result.error(e.getMessage());
        }
        return result;
    }

    @ApiOperation("查询List")
    @GetMapping("/v1/cms/roleList")
    @ResponseBody
    public ResResult<List<RoleMO>> getRoleList(){
        ResResult<List<RoleMO>> result = new ResResult<>();
        try {
            result.success(roleService.getRoleList());
        } catch (Exception e) {
            result.error(e.getMessage());
        }
        return result;
    }
}
