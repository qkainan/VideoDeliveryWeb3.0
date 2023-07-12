package com.feidian.bo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommodityBO {

  private Long id;
  private Long userId;

  private String commodityName;
  private String commodityType;
  private BigDecimal price;
  private String commodityDescription;
  private String commodityAddress;
  private String coverUrl;

  private Long commodityStatus;

  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;
  private Long isDeleted;


  public CommodityBO(Long id, Long userId, String commodityName, String commodityType, BigDecimal price, String commodityDescription, String commodityAddress, String coverUrl) {
    this.id = id;
    this.userId = userId;
    this.commodityName = commodityName;
    this.commodityType = commodityType;
    this.price = price;
    this.commodityDescription = commodityDescription;
    this.commodityAddress = commodityAddress;
    this.coverUrl = coverUrl;
  }

  public CommodityBO(Long id, Long userId, String commodityName, String commodityType, BigDecimal price, String commodityDescription, String commodityAddress) {
    this.id = id;
    this.userId = userId;
    this.commodityName = commodityName;
    this.commodityType = commodityType;
    this.price = price;
    this.commodityDescription = commodityDescription;
    this.commodityAddress = commodityAddress;
  }

}
