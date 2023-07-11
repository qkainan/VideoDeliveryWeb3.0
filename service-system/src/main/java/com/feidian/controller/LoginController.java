package com.feidian.controller;

import com.feidian.po.SysUser;
import com.feidian.responseResult.ResponseResult;
import com.feidian.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody SysUser user){
        return loginService.login(user);
    }
}