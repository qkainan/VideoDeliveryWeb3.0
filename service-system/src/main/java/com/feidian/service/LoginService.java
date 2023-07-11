package com.feidian.service;

import com.feidian.po.SysUser;
import com.feidian.responseResult.ResponseResult;

public interface LoginService {

    ResponseResult login(SysUser user);

    ResponseResult logout();

}
