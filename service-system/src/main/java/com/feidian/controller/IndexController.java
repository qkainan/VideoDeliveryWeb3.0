package com.feidian.controller;

import com.feidian.dto.LoginDTO;
import com.feidian.dto.SignupDTO;
import com.feidian.responseResult.ResponseResult;
import com.feidian.service.UserService;
import com.feidian.service.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @Autowired
    private UserService userService;

    @Autowired
    private UtilService utilService;

    //注册登录
    //快速注册
    @PostMapping("/fastSignup")
    public ResponseResult fastSignup(@RequestBody SignupDTO signupDTO){
        return userService.fastSignup(signupDTO);
    }

    //发送验证码
    @PostMapping("/sendVerifyCode")
    public ResponseResult sendVerifyCode(@RequestBody SignupDTO signupDTO) {
        return utilService.sendVerifyCode(signupDTO.getEmailAddress(), signupDTO.getUsername());
    }

    //邮箱注册
    @PostMapping("/emailSignup")
    public ResponseResult emailSignup(@RequestBody SignupDTO signupDTO) throws Exception {
        return userService.emailSignup(signupDTO);
    }

    //登录
    @PostMapping("/login")
    public ResponseResult login(@RequestBody LoginDTO loginDTO){
        return userService.login(loginDTO);
    }



}
