package com.zlp.dairy.business.repository;

import com.zlp.dairy.business.entity.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import java.util.*;

public interface UserRepository extends
        PagingAndSortingRepository<User, Long>,
        JpaSpecificationExecutor<User> {

    User findUserByUserNameAndStatus(String userName, Integer status);

    User findUserByUserIdAndStatus(String userId, Integer status);

    List<User> findUserByStatus(Integer status);
}
