package com.zlp.dairy.business.repository;

import com.zlp.dairy.business.entity.User;
import org.springframework.data.domain.Page;

public interface UserDao {

    Page<User> findByFilter(String searchStr, Integer nextPage, Integer pageSize);

}
