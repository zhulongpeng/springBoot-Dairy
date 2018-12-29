package com.zlp.dairy.base.security;

import com.zlp.dairy.base.constant.Constant;
import com.zlp.dairy.business.entity.User;
import com.zlp.dairy.business.repository.RoleRepository;
import com.zlp.dairy.business.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User userByUserNameAndStatus = userRepository.findUserByUserNameAndStatus(userName, Constant.Status.vaild);
        List<String> roleList =  roleRepository.findByUserName(userName, Constant.Status.vaild);
        if(userByUserNameAndStatus == null){
            throw new UsernameNotFoundException(String.format("No User found with userName '%s'.", userName));
        } else {
            return new JwtUserDetails(userByUserNameAndStatus.getUserName(), userByUserNameAndStatus.getPassword(), roleList.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
        }
    }
}
