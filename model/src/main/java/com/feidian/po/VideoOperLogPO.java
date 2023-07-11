package com.feidian.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoOperLogPO {

  private long id;
  private String businessType;
  private String method;
  private String requestMethod;
  private String operUsername;
  private String operUrl;
  private String operIp;
  private String operParam;
  private String jsonResult;
  private long status;
  private String errorMsg;

  private java.sql.Timestamp operTime;
  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;
  private long isDeleted;

}
