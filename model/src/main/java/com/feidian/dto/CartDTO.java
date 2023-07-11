package com.feidian.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {

    private long id;
    private long userId;
    private long commodityId;
    private long addressId;
    private BigDecimal commodityNum;


    public CartDTO(long userId, long commodityId, long addressId, BigDecimal commodityNum) {
        this.userId = userId;
        this.commodityId = commodityId;
        this.addressId = addressId;
        this.commodityNum = commodityNum;
    }


}
