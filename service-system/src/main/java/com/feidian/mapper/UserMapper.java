package com.feidian.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.feidian.bo.UserBO;
import com.feidian.po.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper extends BaseMapper<SysUser> {

    void insertUser(UserBO userBO);

    void updateUserInfo(UserBO userBO);

}
