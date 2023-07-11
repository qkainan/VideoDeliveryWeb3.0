package com.feidian.mapper;

import com.feidian.bo.OrderBO;
import com.feidian.po.OrderPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface OrderMapper {
    void insertOrder(OrderBO orderBO);

    void updateOrderStatus(long orderId);

    List<OrderPO> findByBuyerId(long userId);

    List<OrderPO> findBySellerId(long userId);
}
