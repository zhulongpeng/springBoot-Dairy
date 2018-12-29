package com.zlp.dairy.business.repository;


import com.zlp.dairy.business.entity.Role;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import java.util.*;

public interface RoleRepository extends
        PagingAndSortingRepository<Role, Long>,
        JpaSpecificationExecutor<Role> {

    @Query(nativeQuery = true, value = " " +
    " select tr.role_name from tb_role tr, tb_user tu " +
            "where tu.status=?2 and tr.status=?2 " +
            "and tr.role_id = tu.role_id and tu.user_name=?1" +
            " ")
    List<String> findByUserName(String userName, Integer status);

    List<Role> findRoleByStatus(Integer status);

    Role findRoleByRoleIdAndStatus(String roleId, Integer status);

}
