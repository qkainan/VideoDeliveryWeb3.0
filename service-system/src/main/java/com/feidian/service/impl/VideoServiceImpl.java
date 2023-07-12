package com.feidian.service.impl;

import com.feidian.bo.VideoBO;
import com.feidian.dto.VideoDTO;
import com.feidian.mapper.CommodityMapper;
import com.feidian.mapper.UserMapper;
import com.feidian.mapper.VideoCommodityMapper;
import com.feidian.mapper.VideoMapper;
import com.feidian.po.CommodityPO;
import com.feidian.po.UserPO;
import com.feidian.po.VideoCommodityPO;
import com.feidian.po.VideoPO;
import com.feidian.responseResult.ResponseResult;
import com.feidian.service.VideoService;
import com.feidian.util.JwtUtil;
import com.feidian.util.ReceivingFileUtil;
import com.feidian.util.UploadingFileUtil;
import com.feidian.vo.DisplayVideoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private VideoCommodityMapper videoCommodityMapper;

    @Autowired
    private CommodityMapper commodityMapper;

    @Transactional
    @Override
    public ResponseResult receivingVideo(VideoDTO receivingVideoDTO, MultipartFile dataFile, MultipartFile coverFile) {
        Long userId = JwtUtil.getUserId();

        String videoDataUrl = "";
        String videoCoverUrl = "";

        //Todo 最好放在配置文件
        // 定义保存路径
        String uploadVideoCoverDir = "D:/uploads/videos/cover/";
        ReceivingFileUtil.saveFile(coverFile,uploadVideoCoverDir);
        videoCoverUrl = ReceivingFileUtil.saveFile(coverFile,uploadVideoCoverDir);
        receivingVideoDTO.setCoverUrl(videoCoverUrl);

        String uploadVideoDataDir = "D:/uploads/videos/data/";
        ReceivingFileUtil.saveFile(dataFile,uploadVideoDataDir);
        videoDataUrl = ReceivingFileUtil.saveFile(dataFile,uploadVideoDataDir);
        receivingVideoDTO.setDataUrl(videoDataUrl);
        receivingVideoDTO.setVideoName(dataFile.getOriginalFilename());
        receivingVideoDTO.setVideoName(dataFile.getOriginalFilename());

        receivingVideoDTO.setUserId(userId);

        VideoBO videoBO = new VideoBO(receivingVideoDTO.getUserId(), receivingVideoDTO.getVideoName(),
                receivingVideoDTO.getVideoTitle(), receivingVideoDTO.getVideoType(), receivingVideoDTO.getVideoDescription(),
                receivingVideoDTO.getCoverUrl(), receivingVideoDTO.getDataUrl(),1);

        videoMapper.insertVideo(videoBO);

        return ResponseResult.successResult(200,"上传视频成功");
    }

    @Override
    public ResponseResult displayVideo(Long id) throws IOException, URISyntaxException {
        VideoPO videoPO = videoMapper.findByVideoId(id);
        UserPO userPO = userMapper.findById(videoPO.getUserId());

        List<VideoCommodityPO> videoCommodityPOList = videoCommodityMapper.findByVideoId(id);
        List<CommodityPO> commodityPOList = new ArrayList<>();

        for (VideoCommodityPO vc: videoCommodityPOList) {
            CommodityPO byCommodityIdPO = commodityMapper.findByCommodityId(vc.getCommodityId());
            commodityPOList.add(byCommodityIdPO);
        }

        DisplayVideoVO displayVideoVo = new DisplayVideoVO(videoPO.getId(), videoPO.getVideoTitle(), userPO.getId(),
                userPO.getUsername(), videoPO.getDataUrl(), videoPO.getCoverUrl(),
                videoPO.getVideoDescription(), videoPO.getCreateTime(), commodityPOList,
                UploadingFileUtil.getFileVideo(videoPO.getDataUrl()).getFileByte(),
                UploadingFileUtil.getFileImage(videoPO.getCoverUrl()).getFileByte());

        return ResponseResult.successResult(displayVideoVo);
    }

    @Transactional
    @Override
    public ResponseResult updateVideoInfo(VideoDTO videoDTO) {
        VideoBO videoBO = new VideoBO(videoDTO.getVideoId(),videoDTO.getVideoTitle(), videoDTO.getVideoType(),
                videoDTO.getVideoDescription());
        videoMapper.updateVideoInfo(videoBO);
        return ResponseResult.successResult();
    }

    @Transactional
    @Override
    public ResponseResult deleteVideo(Long videoId) {
        videoMapper.deleteVideo(videoId);
        return ResponseResult.successResult();
    }

    @Override
    public ResponseResult beforeUpdateVideoInfo(Long videoId) {
        return ResponseResult.successResult(videoMapper.findByVideoId(videoId));
    }

    @Override
    public ResponseResult viewPerVideos() {
        Long userId = JwtUtil.getUserId();
        List<VideoPO> videoPOList = videoMapper.findByUserId(userId);

        return ResponseResult.successResult(videoPOList);
    }


//    @Override
//    public Long[] homeRecommend() {
//
//        //创建一个新的数组
//        Long[] arr = new Long[40];
//        //把随机数存入数组当中
//        Random r = new Random();
//        for (Integer i = 0; i < arr.length; i++) {
//            //每循环一次，就会生成一个随机数
//            Integer num = r.nextInt(40 + 1);
//            //把生成的随机数存入数组当中
//            arr[i] = num;
//        }
//
//        return arr;
//    }

}

