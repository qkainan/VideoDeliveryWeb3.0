package com.feidian.mapper;

import com.feidian.po.OrderCommodityPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface OrderCommodityMapper {
    void insertOrderCommodity(Long id, Long id1, Long commodityNum);

    OrderCommodityPO findById(Long id);
}
