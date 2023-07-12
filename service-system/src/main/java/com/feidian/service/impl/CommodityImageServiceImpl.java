package com.feidian.service.impl;

import com.feidian.mapper.CommodityImageMapper;
import com.feidian.po.CommodityImagePO;
import com.feidian.service.CommodityImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommodityImageServiceImpl implements CommodityImageService {

    @Autowired
    private CommodityImageMapper commodityImageMapper;

    @Override
    public void insertCommodityImage(Long commodityId, String imageUrl, Long imageStatus ) {
        commodityImageMapper.insertCommodityImage(commodityId, imageUrl, imageStatus);
    }

    @Override
    public List<CommodityImagePO> findByCommodityId(Long CommodityId) {
        return commodityImageMapper.findByCommodityId(CommodityId);
    }
}
