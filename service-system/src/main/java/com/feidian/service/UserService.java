package com.feidian.service;

import com.feidian.dto.UserDTO;
import com.feidian.responseResult.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    ResponseResult updateUserHead(MultipartFile headFile);

    ResponseResult updateUserDescription(UserDTO userDTO);

    ResponseResult viewUserHomepage();
}
