package com.zlp.dairy.business.entity;

import com.zlp.dairy.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_role")
@ApiModel(value = "角色定义表")
public class Role extends BaseEntity {

    @ApiModelProperty(value = "roleId")
    private String roleId;

    @ApiModelProperty(value = "roleName")
    private String roleName;

    @ApiModelProperty(value = "roleDescription")
    private String roleDescription;

    @Column(name = "role_id")
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Column(name = "role_name")
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Column(name = "role_description")
    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }
}
