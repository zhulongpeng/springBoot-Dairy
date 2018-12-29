package com.zlp.dairy.business.Handle;

import com.zlp.dairy.base.constant.Constant;
import com.zlp.dairy.base.util.XaUtil;
import com.zlp.dairy.business.entity.Role;
import com.zlp.dairy.business.model.RoleMO;
import com.zlp.dairy.business.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoleHandle {

    @Autowired
    private RoleRepository roleRepository;

    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    public List<RoleMO> getRoleList() {
        List<Role> roleList = roleRepository.findRoleByStatus(Constant.Status.vaild);
        List<RoleMO> roleMOS = new ArrayList<>();
        if(XaUtil.isNotEmpty(roleList)){
            roleList.forEach( role -> roleMOS.add(copyMO(role, null)));
        }
        return roleMOS;
    }

    public RoleMO getRoleByRoleId(String roleId) {
        Role role = roleRepository.findRoleByRoleIdAndStatus(roleId, Constant.Status.vaild);
        return copyMO(role, null);
    }

    private RoleMO copyMO(Role role, RoleMO roleMO) {
        if(roleMO == null) roleMO = new RoleMO();
        if(XaUtil.isNotEmpty(role.getId())){
            roleMO.setId(role.getId());
        }
        if(XaUtil.isNotEmpty(role.getRoleId())){
            roleMO.setRoleId(role.getRoleId());
        }
        if(XaUtil.isNotEmpty(role.getRoleName())){
            roleMO.setRoleName(role.getRoleName());
        }
        if(XaUtil.isNotEmpty(role.getRoleDescription())){
            roleMO.setRoleDescription(role.getRoleDescription());
        }
        return roleMO;
    }

}
