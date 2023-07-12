package com.feidian.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "table")
public class UserPO {

  @TableId
  private Long id;
  private String username;
  private String password;
  private String nickname;

  private String phone;
  private String headUrl;
  private String userDescription;
  private String emailAddress;

  private Long userStatus;

  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;
  private Long isDeleted;


}
