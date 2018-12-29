package com.zlp.dairy.business.service.impl;

import com.zlp.dairy.base.Service.impl.NumberServiceImpl;
import com.zlp.dairy.base.security.JwtTokenUtil;
import com.zlp.dairy.base.security.JwtUserDetailsServiceImpl;
import com.zlp.dairy.base.util.MyPage;
import com.zlp.dairy.base.util.XaUtil;
import com.zlp.dairy.business.Handle.RoleHandle;
import com.zlp.dairy.business.Handle.UserHandle;
import com.zlp.dairy.business.entity.User;
import com.zlp.dairy.business.model.UserMO;
import com.zlp.dairy.business.model.UserVO;
import com.zlp.dairy.business.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("UserService")
public class UserServiceImpl extends NumberServiceImpl implements UserService {

    @Autowired
    private UserHandle userHandle;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUserDetailsServiceImpl jwtUserDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private RoleHandle roleHandle;

    public UserVO login(String userName, String password) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userName, password);
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(userName);
        String token = jwtTokenUtil.generateToken(userDetails);
        if(XaUtil.isEmpty(token))return null;
        User user = userHandle.findUserByUserName(userName);
        return copyMO(user, new UserVO(), token);
    }

    public String deleteByUserId(String userId) {
        User user = userHandle.findUserByUserId(userId);
        userHandle.deleteUser(user);
        return "SUCCESS";
    }

    public UserVO getUserByUserId(String userId) {
        User user = userHandle.findUserByUserId(userId);
        return copyMO(user, null, null);
    }

    public User findUserByUserId(String userId) {
        return userHandle.findUserByUserId(userId);
    }

    public String updateUser(User user, UserMO userMO) {
        String message = userHandle.updateUser(userMO, user);
        if(XaUtil.isEmpty(message)) return null;
        return "SUCCESS";
    }

    public Page<UserVO> getUserByFilter(String searchStr, Integer nextPage, Integer pageSize) {
       Page<User> userList =  userHandle.findByUserFilter(searchStr, nextPage, pageSize);
       if(CollectionUtils.isEmpty(userList.getContent())) return null;
       List<UserVO> result = new ArrayList<>();
       userList.getContent().forEach( user -> result.add( copyMO(user, null, null)) );
       return new MyPage<>(nextPage, pageSize, result, (int)userList.getTotalElements());
    }

    public List<UserVO> getUser() {
        List<User> userList = userHandle.getUser();
        if(CollectionUtils.isEmpty(userList)) return null;
        List<UserVO> result =  new ArrayList<>();
        userList.forEach( user -> result.add( copyMO(user, null, null)));
        return result;
    }

    public String createUser(UserMO userMO) {
        userMO.setUserId(findByBusinessKey("userId"));
        String message = userHandle.createUser(userMO);
        if(XaUtil.isEmpty(message)) return null;
        return "SUCCESS";
    }

    private UserVO copyMO(User user, UserVO result, String token) {
       if(result == null) result = new UserVO();
       result.setId(user.getId());
       if(XaUtil.isNotEmpty(user.getUserName())){
           result.setUserName(user.getUserName());
       }
       if(XaUtil.isNotEmpty(user.getUserId())){
           result.setUserId(user.getUserId());
       }
       if(XaUtil.isNotEmpty(user.getNickName())){
           result.setNickName(user.getNickName());
       }
       if(XaUtil.isNotEmpty(user.getEmail())){
           result.setEmail(user.getEmail());
       }
       if(XaUtil.isNotEmpty(user.getMobile())){
           result.setMobile(user.getMobile());
       }
       if(XaUtil.isNotEmpty(user.getPassword())){
           result.setPassword(user.getPassword());
       }
       if(XaUtil.isNotEmpty(user.getRoleId())){
           result.setRoleName(roleHandle.getRoleByRoleId(user.getRoleId()).getRoleName());
       }
       if(XaUtil.isNotEmpty(token)){
           result.setToken(token);
       }
       return result;
    }
}
