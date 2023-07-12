package com.feidian.service.impl;

import com.feidian.bo.VideoCommodityBO;
import com.feidian.dto.VideoDTO;
import com.feidian.mapper.VideoCommodityMapper;
import com.feidian.mapper.VideoMapper;
import com.feidian.po.VideoPO;
import com.feidian.responseResult.ResponseResult;
import com.feidian.service.VideoCommodityService;
import com.feidian.util.JwtUtil;
import com.feidian.util.SecurityContextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoCommodityServiceImpl implements VideoCommodityService {

    @Autowired
    private VideoCommodityMapper videoCommodityMapper;

    @Autowired
    private VideoMapper videoMapper;

    @Override
    public ResponseResult uploadVideoCommodity(VideoDTO videoDTO) {
        Long userId = SecurityContextUtils.getUserId();
        VideoPO videoPO = videoMapper.findByVideoId(videoDTO.getVideoId());

        //将商品安排到video推荐商品里
        for (Long cId : videoDTO.getCommodityIdList()) {
            VideoCommodityBO videoCommodityBO = new VideoCommodityBO(userId, videoDTO.getVideoId(), cId,
                    videoPO.getVideoStatus());
            videoCommodityMapper.insertVideoCommodity(videoCommodityBO);
        }
        return ResponseResult.successResult(200, "操作成功");
    }

    @Override
    public ResponseResult updateVideoCommodityInfo(VideoDTO videoDTO) {

        for (Long cId:videoDTO.getCommodityIdList()) {
            VideoCommodityBO videoCommodityBO = new VideoCommodityBO(videoDTO.getVideoId(),cId);
            videoCommodityMapper.updateVideoCommodityInfo(videoCommodityBO);
        }
        return ResponseResult.successResult();
    }

//    @Override
//    public void insertVideoCommodity(VideoCommodityPO videoCommodityPO) {
//        videoCommodityMapper.insertVideoCommodity(videoCommodityPO);
//    }
//
//    @Override
//    public List<VideoCommodityPO> findByVideoId(Long videoId) {
//        return videoCommodityMapper.findByVideoId(videoId);
//    }
//
//    @Override
//    public void updateVideoCommodityMsg(VideoCommodityPO videoCommodityPO) {
//        videoCommodityMapper.updateVideoCommodityMsg(videoCommodityPO);
//    }



}
