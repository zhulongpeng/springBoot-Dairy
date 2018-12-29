package com.zlp.dairy.business.Handle;

import com.zlp.dairy.base.constant.Constant;
import com.zlp.dairy.business.entity.User;
import com.zlp.dairy.business.model.UserMO;
import com.zlp.dairy.business.repository.UserDao;
import com.zlp.dairy.business.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserHandle {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDao userDao;

    public String createUser(UserMO userMO){
        User user = new User();
        user.setUserId(userMO.getUserId());
        save(copyForEntity(userMO, user));
        return "SUCCESS";
    }

    private User save(User user){
        return userRepository.save(user);
    }

    public User findUserByUserName(String userName) {
        return userRepository.findUserByUserNameAndStatus(userName, Constant.Status.vaild);
    }

    public User findUserByUserId(String userId) {
        return userRepository.findUserByUserIdAndStatus(userId, Constant.Status.vaild);
    }

    public User deleteUser(User user) {
        user.setStatus(Constant.Status.delete);
        return userRepository.save(user);
    }

    public String updateUser(UserMO userMO, User user) {
        save(copyForEntity(userMO, user));
        return "SUCCESS";
    }

    public Page<User> findByUserFilter(String searchStr, Integer nextPage, Integer pageSize) {
        return userDao.findByFilter(searchStr, nextPage, pageSize);
    }

    public List<User> getUser() {
        return userRepository.findUserByStatus(Constant.Status.vaild);
    }

    private User copyForEntity(UserMO userMO, User result) {
        result = result == null ? new User() : result;
        if(StringUtils.isNotBlank(userMO.getEmail())){
            result.setEmail(userMO.getEmail());
        }
        if(StringUtils.isNotBlank(userMO.getMobile())){
            result.setMobile(userMO.getMobile());
        }
        if(StringUtils.isNotBlank(userMO.getNickName())){
            result.setNickName(userMO.getNickName());
        }
        if(StringUtils.isNotBlank(userMO.getPassword())){
            result.setPassword(new BCryptPasswordEncoder().encode(userMO.getPassword()));
        }
        if(StringUtils.isNotBlank(userMO.getRoleId())){
            result.setRoleId(userMO.getRoleId());
        }
        if(StringUtils.isNotBlank(userMO.getUserName())){
            result.setUserName(userMO.getUserName());
        }
        if(StringUtils.isNotBlank(userMO.getSessionKey())){
            result.setSessionKey(userMO.getSessionKey());
        }
        return result;
    }
}


