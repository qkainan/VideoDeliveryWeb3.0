package com.feidian.service;

import com.feidian.dto.CartDTO;
import com.feidian.dto.PurchaseDTO;
import com.feidian.responseResult.ResponseResult;

public interface CartService {
    ResponseResult displayCartVOList();

    ResponseResult uploadCart(CartDTO cartDTO);

    ResponseResult deleteCart(Long cartId);

    ResponseResult cartPurchase(PurchaseDTO purchaseDTO);
}
