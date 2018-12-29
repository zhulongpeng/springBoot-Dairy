package com.zlp.dairy.business.service;

import com.zlp.dairy.business.model.RoleMO;

import java.util.List;

public interface RoleService {

    String createRole(String roleName);

    List<RoleMO> getRoleList();

    RoleMO getRoleByRoleId(String roleId);
}
