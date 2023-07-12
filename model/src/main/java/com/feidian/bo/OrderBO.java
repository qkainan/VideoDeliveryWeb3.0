package com.feidian.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderBO {

  private Long id;
  private Long buyerId;
  private Long sellerId;
  private String addressName;

  private Long orderStatus;

  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;
  private Long isDeleted;


  public OrderBO(Long buyerId, Long sellerId, String addressName, Long orderStatus) {
    this.buyerId = buyerId;
    this.sellerId = sellerId;
    this.addressName = addressName;
    this.orderStatus = orderStatus;
  }

}
