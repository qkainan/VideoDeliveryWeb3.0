package com.feidian.mapper;

import com.feidian.bo.VideoCommodityBO;
import com.feidian.po.VideoCommodityPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface VideoCommodityMapper {
    void insertVideoCommodity(VideoCommodityBO videoCommodityBO);

    void updateVideoCommodityInfo(VideoCommodityBO videoCommodityBO);

    List<VideoCommodityPO> findByVideoId(long videoId);

}
