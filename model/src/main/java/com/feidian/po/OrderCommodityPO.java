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
@TableName(value = "order_commodity")
public class OrderCommodityPO {

  @TableId
  private Long id;
  private Long orderId;
  private Long commodityId;
  private double commodityNum;

  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;
  private Long isDeleted;



}
