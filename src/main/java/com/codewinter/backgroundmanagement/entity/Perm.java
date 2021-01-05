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
public class Perm {
   private Long id;
   private String permName;
   private String url;
   private int level;
   private Long parentModule;
   private Character visible;
   private Character type;
   private String authorityString;

   @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
   @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
   private Date addTime;
   @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
   @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
   private Date updateTime;
}
