package com.feidian.service.impl;

import com.feidian.bo.LoginUser;
import com.feidian.bo.UserBO;
import com.feidian.dto.LoginDTO;
import com.feidian.dto.SignupDTO;
import com.feidian.enums.HttpCodeEnum;
import com.feidian.mapper.UserMapper;
import com.feidian.po.SysUser;
import com.feidian.responseResult.ResponseResult;
import com.feidian.service.AuthenticationService;
import com.feidian.service.UserService;
import com.feidian.util.JwtUtil;
import com.feidian.util.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult login(LoginDTO user) {
        if (user.getPassword().length() >16 && user.getPassword().length() <8 ) {
            return ResponseResult.errorResult(403, "密码不符合要求");
        }

        //AuthenticationManager authenticate进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
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

        return ResponseResult.successResult(map);
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

    @Override
    public ResponseResult fastSignup(SignupDTO signupDTO) {
        //Todo 校验密码是否符合强度要求
        // 1.只能包含英文字母、阿拉伯数字和下划线
        // 2.密码长度在8到25之间
        // 3.再次输入密码需与第一次输入的密码一致
        // 4.加密密码
        String regexPwd = "\\w{8,25}";
        if (signupDTO.getUsername()==null){
            return ResponseResult.errorResult(HttpCodeEnum.REQUIRE_USERNAME);
        }

        if (signupDTO.getPassword().matches(regexPwd) == false) {
            return ResponseResult.errorResult(403, "密码不符合要求");
        }

        if (!signupDTO.getPassword().equals(signupDTO.getRePwd())) {
            return ResponseResult.errorResult(403, "第二次输入密码与第一次不同");
        }

        //加密密码并创建用户
        //补全16位
        String encryptUserPwd = null;

        encryptUserPwd = bCryptPasswordEncoder.encode(signupDTO.getPassword());
        UserBO userBO = new UserBO(signupDTO.getUsername(), encryptUserPwd, signupDTO.getNickname());
        userMapper.insertUser(userBO);
        return ResponseResult.successResult(200, "快速注册成功");
    }


    @Override
    public ResponseResult emailSignup(SignupDTO signupDTO) {
        //Todo 校验密码是否符合强度要求
        // 1.只能包含英文字母、阿拉伯数字和下划线
        // 2.密码长度在8到16之间
        // 3.再次输入密码需与第一次输入的密码一致
        // 4.加密密码
        // 5.邮箱验证
        String regexPwd = "\\w{8,16}";

        if (signupDTO.getUsername()==null){
            return ResponseResult.errorResult(HttpCodeEnum.REQUIRE_USERNAME);
        }

        if (false == signupDTO.getPassword().matches(regexPwd)) {
            return ResponseResult.errorResult(403,"密码不符合要求");
        }

        if (!signupDTO.getPassword().equals(signupDTO.getRePwd())) {
            return ResponseResult.successResult(403, "第二次输入密码与第一次不同");
        }

        String regexEmailAddress = "\\w+@[\\w&&[^_]]{2,7}(\\.[a-zA-Z]{2,4}){1,3}";

        if (!signupDTO.getEmail().matches(regexEmailAddress)) {
            return ResponseResult.errorResult(403,"邮箱格式不正确");
        }

        //验证邮箱验证码
        Boolean verifyResult = (redisCache.getCacheList(signupDTO.getUsername()+ "verifyCode")).equals(signupDTO.getVerifyCode());

        if (false == verifyResult) {
            return  ResponseResult.errorResult(403,"验证码错误");
        }

        String encryptUserPwd = bCryptPasswordEncoder.encode(signupDTO.getPassword());
        UserBO userBO = new UserBO(signupDTO.getUsername(), encryptUserPwd,
                signupDTO.getNickname(), signupDTO.getEmail());
        userMapper.insertUser(userBO);
        return ResponseResult.successResult(200, "邮箱注册成功");
    }
}
