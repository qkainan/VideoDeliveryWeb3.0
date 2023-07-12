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
public class CartBO {

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

}
