package com.feidian.service;

import com.feidian.dto.VideoDTO;
import com.feidian.responseResult.ResponseResult;


public interface VideoCommodityService {

    ResponseResult uploadVideoCommodity(VideoDTO videoDTO);

    ResponseResult updateVideoCommodityInfo(VideoDTO videoDTO);
}
