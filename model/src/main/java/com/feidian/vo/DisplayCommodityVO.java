package com.feidian.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DisplayCommodityVO {

  private long id;
  private long userId;

  private String commodityName;
  private String commodityType;
  private BigDecimal price;
  private String commodityDescription;
  private String commodityAddress;
  private String coverUrl;

  private long commodityStatus;

  private byte[] coverResource;
  private List<byte[]> imageResource;

  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;
  private long isDeleted;


  public DisplayCommodityVO(long id, long userId, String commodityName, String commodityType, BigDecimal price, String commodityDescription, String commodityAddress, String coverUrl) {
    this.id = id;
    this.userId = userId;
    this.commodityName = commodityName;
    this.commodityType = commodityType;
    this.price = price;
    this.commodityDescription = commodityDescription;
    this.commodityAddress = commodityAddress;
    this.coverUrl = coverUrl;
  }



  public DisplayCommodityVO(long id, long userId, String commodityName, String commodityType, BigDecimal price, String commodityDescription, String commodityAddress, long commodityStatus, byte[] coverResource, List<byte[]> imageResource) {
    this.id = id;
    this.userId = userId;
    this.commodityName = commodityName;
    this.commodityType = commodityType;
    this.price = price;
    this.commodityDescription = commodityDescription;
    this.commodityAddress = commodityAddress;
    this.commodityStatus = commodityStatus;
    this.coverResource = coverResource;
    this.imageResource = imageResource;

  }

}
