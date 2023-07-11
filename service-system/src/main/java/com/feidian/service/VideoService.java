package com.feidian.service;

import com.feidian.dto.VideoDTO;
import com.feidian.responseResult.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;


public interface VideoService {


//    long[] homeRecommend();

//    List<VideoPO> findByUserId(long userId);


    ResponseResult receivingVideo(VideoDTO receivingVideoDTO, MultipartFile dataFile, MultipartFile coverFile);

    ResponseResult displayVideo(long id) throws IOException, URISyntaxException;

    ResponseResult updateVideoInfo(VideoDTO videoDTO);

    ResponseResult deleteVideo(long videoId);

    ResponseResult beforeUpdateVideoInfo(long videoId);

    ResponseResult viewPerVideos();
}
