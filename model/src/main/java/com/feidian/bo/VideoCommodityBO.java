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

  private long id;
  private long userId;
  private long videoId;
  private long commodityId;

  private long videoStatus;



  public VideoCommodityBO(long userId, long videoId, long commodityId, long videoStatus) {
    this.userId = userId;
    this.videoId = videoId;
    this.commodityId = commodityId;
    this.videoStatus = videoStatus;
  }

  public VideoCommodityBO(long videoId, long commodityId) {
    this.videoId = videoId;
    this.commodityId = commodityId;
  }

}
