package com.feidian.service;

import com.feidian.dto.LoginDTO;
import com.feidian.dto.SignupDTO;
import com.feidian.dto.UserDTO;
import com.feidian.responseResult.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    ResponseResult fastSignup(SignupDTO signupDTO);

    ResponseResult emailSignup(SignupDTO signupDTO);

    ResponseResult login(LoginDTO loginDTO);

    ResponseResult updateUserHead(MultipartFile headFile);

    ResponseResult updateUserDescription(UserDTO userDTO);

    ResponseResult viewUserHomepage();
}
