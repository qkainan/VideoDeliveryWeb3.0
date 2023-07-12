package com.feidian.controller;

import com.feidian.dto.VideoDTO;
import com.feidian.responseResult.ResponseResult;
import com.feidian.service.VideoCommodityService;
import com.feidian.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;


@RestController
public class VideoController {

    @Autowired
    private VideoService videoService;

    @Autowired
    private VideoCommodityService videoCommodityService;


    //上传视频
    @PostMapping(value = "/uploadVideo", consumes = "multipart/form-data")
    public ResponseResult receivingVideo( @RequestPart("uploadVideoVo") VideoDTO receivingVideoDTO,
                                          @RequestPart("dataFile") MultipartFile dataFile,
                                          @RequestPart("coverFile") MultipartFile coverFile){
        return videoService.receivingVideo(receivingVideoDTO, dataFile, coverFile);
    }

    //展示视频
    @GetMapping("/displayVideo")
    public ResponseResult displayVideo(Long id) throws IOException, URISyntaxException {
       return videoService.displayVideo(id);
    }

    @PutMapping("/updateVideoInfo")
    public ResponseResult updateVideoInfo(@RequestBody VideoDTO videoDTO) {
        return videoService.updateVideoInfo(videoDTO);
    }

    @Transactional
    @GetMapping("/viewPerVideos")
    public ResponseResult viewPerVideos(){
        return videoService.viewPerVideos();
    }

    @PostMapping ("/deleteVideo")
    public ResponseResult deleteVideo(Long videoId){
        return videoService.deleteVideo(videoId);
    }

    //给视频添加推荐商品
    @PostMapping("/uploadVideoCommodity")
    public ResponseResult uploadVideoCommodity(@RequestBody VideoDTO videoDTO){
        return videoCommodityService.uploadVideoCommodity(videoDTO);
    }

    //更改商品的推荐商品
    @PutMapping("/updateVideoCommodityInfo")
    public ResponseResult putUpdateVideoCommodityMsg(@RequestBody VideoDTO videoDTO){
        return videoCommodityService.updateVideoCommodityInfo(videoDTO);
    }

    //在更新视频信息之前，将已有的信息带上，避免更新部分信息覆盖之前的信息的情况
    @GetMapping("/beforeUpdateVideoInfo")
    public ResponseResult beforeUpdateVideoInfo(Long videoId){
        return videoService.beforeUpdateVideoInfo(videoId);
    }

//    @GetMapping("/getHomeRecommend")
//    public ResponseResult homeRecommend() throws IOException, URISyntaxException {
//        List<VideoPO> list = new ArrayList<>();
//
//        HashMap<Long,byte[]> map = new HashMap<>();
//
//        for (Long videoId:videoService.homeRecommend()) {
//            VideoPO videoPO = videoService.findByVideoId(videoId);
//            list.add(videoPO);
//            CoverVo coverVo = new CoverVo();
//            /*DataAndCoverResource dataAndCoverResource =new DataAndCoverResource(fileUploadUtil.getFileVideo(videoPO.getDataUrl()).getFileByte(),
//                    fileUploadUtil.getFileImage(videoPO.getCoverUrl()).getFileByte());*/
//
//            byte[] coverFile = fileUploadUtil.getFileImage(videoPO.getCoverUrl()).getFileByte();
//            map.put(videoPO.getId(),coverFile);
//        }
//        return new ResponseResult(200, "操作成功",map);
//    }


}
