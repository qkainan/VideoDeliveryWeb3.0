package com.feidian.service.impl;


import com.feidian.mapper.CommodityMapper;
import com.feidian.mapper.OrderCommodityMapper;
import com.feidian.mapper.OrderMapper;
import com.feidian.po.CommodityPO;
import com.feidian.po.OrderCommodityPO;
import com.feidian.po.OrderPO;
import com.feidian.responseResult.ResponseResult;
import com.feidian.service.OrderService;
import com.feidian.util.JwtUtil;
import com.feidian.vo.PurchaseOrderVO;
import com.feidian.vo.SaleOrderVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@Mapper
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderCommodityMapper orderCommodityMapper;

    @Autowired
    private CommodityMapper commodityMapper;


    //收货
    @Override
    public ResponseResult receivingCommodity(long orderId) {
        orderMapper.updateOrderStatus(orderId);
        return ResponseResult.successResult();
    }

    @Transactional
    @Override
    public ResponseResult viewPurchaseOrder() {
        List<PurchaseOrderVO> purchaseOrderVoList = new ArrayList<>();

        for (OrderPO o : orderMapper.findByBuyerId(JwtUtil.getUserId())) {

            OrderCommodityPO orderCommodity = orderCommodityMapper.findById(o.getId());
            CommodityPO commodityPO = commodityMapper.findByCommodityId(orderCommodity.getCommodityId());

            PurchaseOrderVO purchaseOrderVo = new PurchaseOrderVO(o.getId(), commodityPO.getCommodityName(), commodityPO.getPrice(),
                    commodityPO.getCommodityAddress(), o.getOrderStatus(), o.getUpdateTime());

            purchaseOrderVoList.add(purchaseOrderVo);
        }

        return ResponseResult.successResult(purchaseOrderVoList);
    }

    @Transactional
    @Override
    public ResponseResult viewSaleOrder() {
        List<SaleOrderVO> saleOrderVoList = new ArrayList<>();

        for (OrderPO o : orderMapper.findBySellerId(JwtUtil.getUserId())) {

            OrderCommodityPO orderCommodity = orderCommodityMapper.findById(o.getId());
            CommodityPO commodityPO = commodityMapper.findByCommodityId(orderCommodity.getCommodityId());

            SaleOrderVO saleOrderVo = new SaleOrderVO(o.getId(), commodityPO.getCommodityName(), commodityPO.getPrice(),
                    commodityPO.getCommodityAddress(), o.getOrderStatus(), o.getUpdateTime());

            saleOrderVoList.add(saleOrderVo);
        }
        return ResponseResult.successResult(saleOrderVoList);
    }


}
