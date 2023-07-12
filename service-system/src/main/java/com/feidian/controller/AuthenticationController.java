package com.feidian.controller;

import com.feidian.dto.LoginDTO;
import com.feidian.dto.SignupDTO;
import com.feidian.po.SysUser;
import com.feidian.responseResult.ResponseResult;
import com.feidian.service.AuthenticationService;
import com.feidian.service.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    private UtilService utilService;

    @Autowired
    private AuthenticationService authenticationService;

    //发送验证码
    @PostMapping("/sendVerifyCode")
    public ResponseResult sendVerifyCode(@RequestBody SignupDTO signupDTO) {
        return utilService.sendVerifyCode(signupDTO.getEmailAddress(), signupDTO.getUsername());
    }

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody LoginDTO user){
        return authenticationService.login(user);
    }

    @GetMapping("/user/logout")
    public ResponseResult logout(){
        return authenticationService.logout();
    }

    //注册登录
    //快速注册
    @PostMapping("/user/fastSignup")
    public ResponseResult fastSignup(@RequestBody SignupDTO signupDTO){
        return authenticationService.fastSignup(signupDTO);
    }

    //邮箱注册
    @PostMapping("/user/emailSignup")
    public ResponseResult emailSignup(@RequestBody SignupDTO signupDTO) throws Exception {
        return authenticationService.emailSignup(signupDTO);
    }

}
