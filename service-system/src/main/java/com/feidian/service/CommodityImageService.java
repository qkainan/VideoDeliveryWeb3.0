package com.feidian.service;

import com.feidian.po.CommodityImagePO;

import java.util.List;

public interface CommodityImageService {

    void insertCommodityImage(long commodityId, String imageUrl, long imageStatus);

    List<CommodityImagePO> findByCommodityId(long CommodityId);
}
