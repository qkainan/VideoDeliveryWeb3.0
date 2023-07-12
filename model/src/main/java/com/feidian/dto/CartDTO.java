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

    private Long id;
    private Long userId;
    private Long commodityId;
    private Long addressId;
    private BigDecimal commodityNum;


    public CartDTO(Long userId, Long commodityId, Long addressId, BigDecimal commodityNum) {
        this.userId = userId;
        this.commodityId = commodityId;
        this.addressId = addressId;
        this.commodityNum = commodityNum;
    }


}
