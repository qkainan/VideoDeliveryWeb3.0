package com.feidian.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPO {

  private long id;
  private String username;
  private String password;
  private String nickname;

  private String phone;
  private String headUrl;
  private String userDescription;
  private String emailAddress;

  private long userStatus;

  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;
  private long isDeleted;


}
