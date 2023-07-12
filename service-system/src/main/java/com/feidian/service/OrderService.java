package com.feidian.service;

import com.feidian.responseResult.ResponseResult;

public interface OrderService {
    ResponseResult receivingCommodity(Long orderId);

    ResponseResult viewPurchaseOrder();

    ResponseResult viewSaleOrder();
}
