package com.feidian.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDTO {

    private Long id;
    private Long orderId;
    private Long commodityId;
    private Long commodityNum;
    //收货地址
    private Long addressId;

}