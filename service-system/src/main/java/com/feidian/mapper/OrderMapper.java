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

    void updateOrderStatus(Long orderId);

    List<OrderPO> findByBuyerId(Long userId);

    List<OrderPO> findBySellerId(Long userId);
}
