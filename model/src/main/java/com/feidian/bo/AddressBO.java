package com.feidian.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressBO {

  private Long id;
  private Long userId;
  private String addressName;

  private Long addressStatus;

  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;
  private Long isDeleted;

  public AddressBO(Long id, Long userId, String addressName) {
    this.id = id;
    this.userId = userId;
    this.addressName = addressName;
  }

}
