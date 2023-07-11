package com.feidian.service.impl;

import com.feidian.bo.LoginUser;
import com.feidian.po.SysUser;
import com.feidian.enums.HttpCodeEnum;
import com.feidian.responseResult.ResponseResult;
import com.feidian.service.LoginService;
import com.feidian.util.JwtUtil;
import com.feidian.util.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult login(SysUser user) {

        //AuthenticationManager authenticate进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        //如果认证没通过，给出对应的提示
        if (Objects.isNull(authentication)){
            return ResponseResult.errorResult(HttpCodeEnum.LOGIN_ERROR);
        }

        //如果认证通过，使用userid生成一个jwt 存入ResponseResult
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String userId = String.valueOf(loginUser.getUser().getId());
        String jwt = JwtUtil.createJWT(userId);

        Map<String,String> map = new HashMap<>();
        map.put("token",jwt);
        //把完整的与用户信息存入redis，userid作为key
        redisCache.setCacheObject("login:" + userId , loginUser);

        return ResponseResult.successResult(200,"登陆成功");
    }

    @Override
    public ResponseResult logout() {
        //从SecurityContextHolder中获取userId
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getId();
        //删除redis中的值
        redisCache.deleteObject("login:"+userId);
        return ResponseResult.successResult(200,"注销成功");
    }
}
