package com.zlp.dairy;

import com.zlp.dairy.base.util.ResResult;
import com.zlp.dairy.business.model.UserVO;
import com.zlp.dairy.business.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginTest extends DairyApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    public void loginTest(){
        String userName = "test";
        String password = "123456";
        ResResult<UserVO> result = new ResResult<>();
        result.success(userService.login(userName, password));
        System.out.println(result);
    }
}
