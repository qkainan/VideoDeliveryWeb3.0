package com.feidian.controller;

import com.feidian.dto.SignupDTO;
import com.feidian.responseResult.ResponseResult;
import com.feidian.service.AuthenticationService;
import com.feidian.service.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
