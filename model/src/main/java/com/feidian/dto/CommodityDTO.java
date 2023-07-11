package com.feidian.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommodityDTO {

    private long commodityId;

    private  long userId;
    private String commodityName;
    private String commodityType;
    private BigDecimal price;
    private String commodityAddress;

    private String commodityDescription;

    private String coverUrl;
    private List<String> imageUrl;

    public CommodityDTO(long commodityId, String commodityName, String commodityType, BigDecimal price, String commodityAddress, String commodityDescription) {
        this.commodityId = commodityId;
        this.commodityName = commodityName;
        this.commodityType = commodityType;
        this.price = price;
        this.commodityAddress = commodityAddress;
        this.commodityDescription = commodityDescription;
    }

}
