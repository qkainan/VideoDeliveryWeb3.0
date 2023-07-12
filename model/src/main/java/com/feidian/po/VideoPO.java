package com.feidian.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "video")
public class VideoPO {

  @TableId
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



}
