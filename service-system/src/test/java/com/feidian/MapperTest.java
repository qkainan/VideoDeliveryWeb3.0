package com.feidian;

import com.feidian.mapper.UserMapper;
import com.feidian.po.SysUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootTest
public class MapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testUserMapper(){
        List<SysUser> users = userMapper.selectList(null);
        System.out.println(users);
    }

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Test
    public void test(){
        String s="123456";

        String encode = bCryptPasswordEncoder.encode(s);
        System.out.println(encode);
    }
}