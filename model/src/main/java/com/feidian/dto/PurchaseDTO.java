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

    private long id;
    private long orderId;
    private long commodityId;
    private long commodityNum;
    //收货地址
    private long addressId;

}