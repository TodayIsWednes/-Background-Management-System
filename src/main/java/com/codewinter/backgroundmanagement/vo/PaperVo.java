package com.codewinter.backgroundmanagement.vo;

import com.codewinter.backgroundmanagement.entity.Paper;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class PaperVo {
   /**
    * 试卷的基本信息
    */
   private Long id;
   private String paperName;
   private Integer duration;
   private Long courseId;
   private Integer totalScore;
   private String status;

   public void setPropertyFromPaper(Paper paper){
      this.id=paper.getId();
      this.paperName=paper.getPaperName();
      this.duration=paper.getDuration();

      switch(paper.getStatus()){
         case 0:
            this.status="禁用";
            break;
         case 1:
            this.status="启用";
            break;
         default:
            break;
      }

      this.testTime=paper.getTestTime();
   }

   @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
   @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
   private Date testTime;

   @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
   @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
   private Date createTime;

   @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
   @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
   private Date updateTime;
}
