package com.feidian.service;

import com.feidian.responseResult.ResponseResult;

public interface OrderService {
    ResponseResult receivingCommodity(long orderId);

    ResponseResult viewPurchaseOrder();

    ResponseResult viewSaleOrder();
}
