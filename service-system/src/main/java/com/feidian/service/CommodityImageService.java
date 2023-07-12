package com.feidian.service;

import com.feidian.po.CommodityImagePO;

import java.util.List;

public interface CommodityImageService {

    void insertCommodityImage(Long commodityId, String imageUrl, Long imageStatus);

    List<CommodityImagePO> findByCommodityId(Long CommodityId);
}
