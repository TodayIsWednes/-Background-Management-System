package com.codewinter.backgroundmanagement.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Paper {
   /**
    * 试卷的基本信息
    */
   private Long id;
   private String paperName;
   private Integer duration;
   private Long courseId;
   private Integer totalScore;
   private Integer status;


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
