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
public class PurchaseOrderVO {

    private long id;
    private String commodityName;

    private BigDecimal price;

    private String commodityAddress;

    private long status;

    private java.sql.Timestamp updateTime;

}
