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

    private long id;
    private long userId;
    private long commodityId;
    private long addressId;

    private String commodityDescription;
    private BigDecimal price;
    private BigDecimal commodityNum;
    private BigDecimal totalPrice;

    //1：已购买 0：未购买
    private long orderStatus;


    private java.sql.Timestamp updateTime;


    public CartVO(long id, long userId, long commodityId, long addressId, String commodityDescription, BigDecimal price, BigDecimal commodityNum, BigDecimal totalPrice, long orderStatus) {
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
