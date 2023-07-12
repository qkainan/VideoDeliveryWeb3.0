package com.feidian.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.feidian.bo.UserBO;
import com.feidian.po.SysUser;
import com.feidian.po.UserPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper extends BaseMapper<SysUser> {

    void insertUser(UserBO userBO);

    UserPO findByName(String username);

    void updateUserInfo(UserBO userBO);

    UserPO findById(Long id);

}
