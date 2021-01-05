package com.codewinter.backgroundmanagement.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Course {
   private Long id;
   private String courseName;
   private String description;

   @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
   @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
   private Date createTime;

   @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
   @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
   private Date updateTime;

   private List<Paper> paperList;

}
