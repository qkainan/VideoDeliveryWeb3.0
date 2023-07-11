package com.feidian.mapper;

import com.feidian.po.OrderCommodityPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface OrderCommodityMapper {
    void insertOrderCommodity(long id, long id1, long commodityNum);

    OrderCommodityPO findById(long id);
}
