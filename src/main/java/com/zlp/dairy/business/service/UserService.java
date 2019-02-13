package com.zlp.dairy.business.service;

import com.zlp.dairy.business.entity.User;
import com.zlp.dairy.business.model.UserMO;
import com.zlp.dairy.business.model.UserVO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    String createUser(UserMO userMO);

    UserVO login(String userName, String password);

    String deleteByUserId(String userId);

    UserVO getUserByUserId(String userId);

    User findUserByUserId(String userId);

    String updateUser(User user, UserMO userMO);

    Page<UserVO> getUserByFilter(String searchStr, Integer nextPage, Integer pageSize);

    List<UserVO> getUser();
}
