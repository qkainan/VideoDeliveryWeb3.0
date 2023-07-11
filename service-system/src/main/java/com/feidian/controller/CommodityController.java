package com.feidian.controller;

import com.feidian.dto.CommodityDTO;
import com.feidian.responseResult.ResponseResult;
import com.feidian.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;


@RestController
public class CommodityController {

    @Autowired
    private CommodityService commodityService;

    @PostMapping(value = "/uploadCommodity", consumes = "multipart/form-data")
    public ResponseResult uploadCommodity(@RequestPart(name = "commodityDTO") CommodityDTO commodityDTO,
                                          @RequestPart(name = "coverFile") MultipartFile coverFile,
                                          @RequestPart(name = "imageFile") MultipartFile[] imageFile) {
        return commodityService.uploadCommodity(commodityDTO, coverFile, imageFile);
    }

    @GetMapping("/displayCommodity")
    public ResponseResult displayCommodity(long commodityId) throws IOException, URISyntaxException {
        return commodityService.displayCommodity(commodityId);
    }

    @GetMapping("/viewPerCommodities")
    public ResponseResult viewPerCommodities(){
        return commodityService.viewPerCommodities();
    }

    @PostMapping("/deleteCommodity")
    public ResponseResult deleteCommodity(long commodityId){
        return commodityService.deleteCommodity(commodityId);
    }

    @PutMapping("/updateCommodityInfo")
    public ResponseResult updateCommodityInfo(@RequestBody CommodityDTO commodityDTO){
        return commodityService.updateCommodityInfo(commodityDTO);
    }

    //在更新商品信息之前，将已有的信息带上，避免更新部分信息覆盖之前的信息的情况
    @GetMapping("/beforeUpdateCommodityInfo")
    public ResponseResult beforeUpdateCommodityInfo(long commodityId){
        return commodityService.beforeUpdateCommodityInfo(commodityId);
    }

}
