package com.feidian.mapper;

import com.feidian.po.CommodityImagePO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CommodityImageMapper {
    void insertCommodityImage(long commodityId, String imageUrl, long imageStatus);

    List<CommodityImagePO> findByCommodityId(long commodityId);

}
