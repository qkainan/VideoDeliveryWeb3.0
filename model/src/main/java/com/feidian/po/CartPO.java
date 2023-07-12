package com.feidian.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "cart")
public class CartPO {

  @TableId
  private Long id;
  private Long userId;
  private Long commodityId;
  private Long addressId;
  private BigDecimal commodityNum;

  private Long orderStatus;

  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;
  private Long isDeleted;



}
