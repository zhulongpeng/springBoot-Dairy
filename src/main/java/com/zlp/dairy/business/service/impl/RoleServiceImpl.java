package com.zlp.dairy.business.service.impl;

import com.zlp.dairy.base.Service.impl.NumberServiceImpl;
import com.zlp.dairy.business.Handle.RoleHandle;
import com.zlp.dairy.business.entity.Role;
import com.zlp.dairy.business.model.RoleMO;
import com.zlp.dairy.business.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("RoleService")
public class RoleServiceImpl extends NumberServiceImpl implements RoleService {

    @Autowired
    private RoleHandle roleHandle;

    public String createRole(String roleName) {
        Role role = new Role();
        role.setRoleName(roleName);
        role.setRoleId(findByBusinessKey("Role"));
        roleHandle.createRole(role);
        return "SUCCESS";
    }

    public List<RoleMO> getRoleList() {
        return roleHandle.getRoleList();
    }

    public RoleMO getRoleByRoleId(String roleId) {
        return roleHandle.getRoleByRoleId(roleId);
    }
}
