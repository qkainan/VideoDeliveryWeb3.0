package com.feidian.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoBO {

  private Long id;
  private Long userId;

  private String videoName;
  private String videoTitle;
  private String videoType;
  private String videoDescription;
  private String coverUrl;
  private String dataUrl;

  private Long videoStatus;
  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;
  private Long isDeleted;

  public VideoBO(Long userId, String videoName, String videoTitle, String videoType, String videoDescription, String coverUrl, String dataUrl, Long videoStatus) {

    this.userId = userId;
    this.videoName = videoName;
    this.videoTitle = videoTitle;
    this.videoType = videoType;
    this.videoDescription = videoDescription;
    this.coverUrl = coverUrl;
    this.dataUrl = dataUrl;
    this.videoStatus = videoStatus;
  }

  public VideoBO(Long id, String videoTitle, String videoType, String videoDescription) {
    this.id = id;
    this.videoTitle = videoTitle;
    this.videoType = videoType;
    this.videoDescription = videoDescription;

  }

}
