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

  private long id;
  private long buyerId;
  private long sellerId;
  private String addressName;

  private long orderStatus;

  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;
  private long isDeleted;


  public OrderBO(long buyerId, long sellerId, String addressName, long orderStatus) {
    this.buyerId = buyerId;
    this.sellerId = sellerId;
    this.addressName = addressName;
    this.orderStatus = orderStatus;
  }

}
