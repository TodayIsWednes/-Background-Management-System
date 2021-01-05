package com.codewinter.backgroundmanagement.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;


import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class User implements Serializable {

   private Long id;
   private String userName;
   private String passWord;
   private String salt;
   private Integer status;

   @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
   @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
   private Date createTime;

   @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
   @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
   private Date updateTime;

}
