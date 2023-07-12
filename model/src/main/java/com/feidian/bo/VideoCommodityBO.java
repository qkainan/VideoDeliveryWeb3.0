package com.feidian.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoCommodityBO {

  private Long id;
  private Long userId;
  private Long videoId;
  private Long commodityId;

  private Long videoStatus;



  public VideoCommodityBO(Long userId, Long videoId, Long commodityId, Long videoStatus) {
    this.userId = userId;
    this.videoId = videoId;
    this.commodityId = commodityId;
    this.videoStatus = videoStatus;
  }

  public VideoCommodityBO(Long videoId, Long commodityId) {
    this.videoId = videoId;
    this.commodityId = commodityId;
  }

}
