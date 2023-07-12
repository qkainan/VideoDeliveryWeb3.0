package com.feidian.service;

import com.feidian.dto.CommodityDTO;
import com.feidian.responseResult.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;

public interface CommodityService {


    ResponseResult uploadCommodity(CommodityDTO commodityDTO, MultipartFile coverFile, MultipartFile[] imageFile);

    ResponseResult displayCommodity(Long commodityId) throws URISyntaxException, IOException;

    ResponseResult deleteCommodity(Long commodityId);

    ResponseResult updateCommodityInfo(CommodityDTO commodityDTO);

    ResponseResult beforeUpdateCommodityInfo(Long commodityId);

    ResponseResult viewPerCommodities();
}
