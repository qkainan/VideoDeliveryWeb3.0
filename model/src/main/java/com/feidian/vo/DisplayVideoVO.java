package com.feidian.vo;

import com.feidian.po.CommodityPO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DisplayVideoVO {

    private Long videoId;
    private String videoTitle;

    private Long userId;
    private String username;


    private String videoDataUrl;
    private String videoCoverUrl;
    private String videoDescription;
    private java.sql.Timestamp createTime;

    //Todo 以后改成VO
    private List<CommodityPO> commodityPOList;

    private byte[] dataResource;

    private byte[] coverResource;

}
