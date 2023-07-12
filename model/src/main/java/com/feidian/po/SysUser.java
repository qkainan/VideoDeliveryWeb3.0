package com.feidian.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "sys_user")
public class SysUser {

  @TableId
  private Long id;
  private String username;
  private String nickname;
  private String password;

  private String status;
  private String email;
  private String phonenumber;
  private String sex;
  private String avatar;
  private String userType;
  private Long createBy;

  private java.sql.Timestamp createTime;
  private Long updateBy;
  private java.sql.Timestamp updateTime;
  private Long isDelete;


}
