package com.feidian.service;

import com.feidian.dto.LoginDTO;
import com.feidian.dto.SignupDTO;

import com.feidian.responseResult.ResponseResult;


public interface AuthenticationService {

    ResponseResult login(LoginDTO loginDTO);

    ResponseResult logout();

    ResponseResult fastSignup(SignupDTO signupDTO);

    ResponseResult emailSignup(SignupDTO signupDTO);

}
