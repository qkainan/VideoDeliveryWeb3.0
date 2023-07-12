package com.feidian.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartVO {

    private Long id;
    private Long userId;
    private Long commodityId;
    private Long addressId;

    private String commodityDescription;
    private BigDecimal price;
    private BigDecimal commodityNum;
    private BigDecimal totalPrice;

    //1：已购买 0：未购买
    private Long orderStatus;


    private java.sql.Timestamp updateTime;


    public CartVO(Long id, Long userId, Long commodityId, Long addressId, String commodityDescription, BigDecimal price, BigDecimal commodityNum, BigDecimal totalPrice, Long orderStatus) {
        this.id = id;
        this.userId = userId;
        this.commodityId = commodityId;
        this.addressId = addressId;
        this.commodityDescription = commodityDescription;
        this.price = price;
        this.commodityNum = commodityNum;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;

    }


}
