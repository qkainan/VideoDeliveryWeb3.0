package com.feidian.service.impl;

import com.feidian.bo.CommodityBO;
import com.feidian.dto.CommodityDTO;
import com.feidian.mapper.CommodityImageMapper;
import com.feidian.mapper.CommodityMapper;
import com.feidian.po.CommodityImagePO;
import com.feidian.po.CommodityPO;
import com.feidian.responseResult.ResponseResult;
import com.feidian.service.CommodityService;
import com.feidian.util.JwtUtil;
import com.feidian.util.ReceivingFileUtil;
import com.feidian.util.UploadingFileUtil;
import com.feidian.vo.DisplayCommodityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommodityServiceImpl implements CommodityService {

    @Autowired
    private CommodityMapper commodityMapper;

    @Autowired
    private CommodityImageMapper commodityImageMapper;

    @Transactional
    @Override
    public ResponseResult uploadCommodity(CommodityDTO commodityDTO, MultipartFile coverFile, MultipartFile[] imageFile) {
        long userId = JwtUtil.getUserId();

        String commodityCoverUrl = "";
        String uploadCommodityCoverDir = "D:/uploads/commodities/cover/";
        ReceivingFileUtil.saveFile(coverFile, uploadCommodityCoverDir);
        commodityCoverUrl = ReceivingFileUtil.saveFile(coverFile, uploadCommodityCoverDir);
        commodityDTO.setCoverUrl(commodityCoverUrl);


        commodityDTO.setUserId(userId);

        CommodityBO commodityBO = new CommodityBO(commodityDTO.getCommodityId(), commodityDTO.getUserId(),
                commodityDTO.getCommodityName(), commodityDTO.getCommodityType(),
                commodityDTO.getPrice(), commodityDTO.getCommodityDescription(),
                commodityDTO.getCommodityAddress(), commodityDTO.getCoverUrl());


        String commodityImageUrl = "";
        String uploadCommodityImageDir = "D:/uploads/commodities/image/";
        for (
                MultipartFile multipartFile : imageFile) {
            ReceivingFileUtil.saveFile(multipartFile, uploadCommodityImageDir);
            commodityImageUrl = ReceivingFileUtil.saveFile(multipartFile, uploadCommodityImageDir);
            commodityImageMapper.insertCommodityImage(commodityBO.getId(), commodityImageUrl, 1);
        }

        commodityMapper.insertCommodity(commodityBO);

        return ResponseResult.successResult();
    }

    @Override
    public ResponseResult displayCommodity(long commodityId) throws URISyntaxException, IOException {

        CommodityPO commodityPO = commodityMapper.findByCommodityId(commodityId);

        if (commodityPO != null) {

            List<CommodityImagePO> commodityImageList = commodityImageMapper.findByCommodityId(commodityId);
            List<byte[]> imageResourceList = new ArrayList<>();
            if (commodityImageList != null) {
                for (CommodityImagePO ci : commodityImageList) {
                    imageResourceList.add(UploadingFileUtil.getFileImage(ci.getImageUrl()).getFileByte());
                }
            }

            DisplayCommodityVO displayCommodityVO = new DisplayCommodityVO(commodityPO.getId(), commodityPO.getUserId(),
                    commodityPO.getCommodityName(), commodityPO.getCommodityType(),
                    commodityPO.getPrice(), commodityPO.getCommodityDescription(),
                    commodityPO.getCommodityAddress(), commodityPO.getCommodityStatus(),
                    UploadingFileUtil.getFileImage(commodityPO.getCoverUrl()).getFileByte(),
                    imageResourceList);

            return ResponseResult.successResult(displayCommodityVO);
        } else {
            return ResponseResult.errorResult(404, "未找到商品");
        }


    }

    @Override
    public ResponseResult deleteCommodity(long commodityId) {
        commodityMapper.deleteCommodity(commodityId);
        return  ResponseResult.successResult(200, "删除成功");
    }

    @Override
    public ResponseResult updateCommodityInfo(CommodityDTO commodityDTO) {
        CommodityBO commodityBO = new CommodityBO(commodityDTO.getCommodityId(), JwtUtil.getUserId(),
                commodityDTO.getCommodityName(), commodityDTO.getCommodityType(),
                commodityDTO.getPrice(), commodityDTO.getCommodityDescription(),
                commodityDTO.getCommodityAddress(), commodityDTO.getCoverUrl());

        commodityMapper.updateCommodity(commodityBO);

        return new ResponseResult(200,"更改成功");
    }

    @Override
    public ResponseResult beforeUpdateCommodityInfo(long commodityId) {
        return ResponseResult.successResult(commodityMapper.findByCommodityId(commodityId));
    }

    @Override
    public ResponseResult viewPerCommodities() {
        long userId = JwtUtil.getUserId();
        List<CommodityPO> commodityPOList = commodityMapper.findByUserId(userId);
        return ResponseResult.successResult(commodityPOList);
    }


//    public void updateCommodityDescription(String description){
//        commodityMapper.updateCommodityDescription(description);
//    };
//
//    @Override
//    public List<CommodityPO> findByUserId(long id) {
//        return commodityMapper.findByUserId(id);
//    }
//


}
