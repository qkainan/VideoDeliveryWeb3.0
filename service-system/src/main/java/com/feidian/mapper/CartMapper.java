package com.feidian.mapper;

import com.feidian.bo.CartBO;
import com.feidian.po.CartPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Mapper
public interface CartMapper {

    void insertCart(CartBO cartBO);

    List<CartPO> findByUserId(long userId);

    void deleteCart(long cartId);

    CartPO findByCartId(long id);

    void updateOrderStatus(long id);
}
