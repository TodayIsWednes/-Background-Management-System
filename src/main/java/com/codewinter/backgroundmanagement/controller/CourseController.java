package com.codewinter.backgroundmanagement.controller;

import com.codewinter.backgroundmanagement.dao.CourseDao;
import com.codewinter.backgroundmanagement.entity.Course;
import com.codewinter.backgroundmanagement.service.CourseService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/course")
@Controller
public class CourseController {


   @Autowired
   CourseService courseService;

   //新增课程
   @PostMapping("/add")
   public String addNewCourse(Course course)
   {

      courseService.save(course);
      return "";
   }

   //删除课程
   @PostMapping("/delete")
   public String deleteCourse(Course course){
      int res=-1;
      res=courseService.deleteCourse(course);
      if(res!=-1){
         return "success";
      }else{
         return "failed";
      }
   }

   //修改课程
   @PostMapping("/update")
   public String updateCourse(Course course){
      int res=-1;
      if(course.getId()!=null){
         res=courseService.updateCourseById(course);
         if(res!=-1){
            return "success";
         }else{
            return "failed";
         }
      }else{
         return "data error!";
      }
   }


   //查找课程 根据指定条件查课程
   @PostMapping("/find/{pageNum}/{pageSize}")
   public String findCourse(Course course, @PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize){
      int res=-1;
      PageInfo<Course> pageInfo =courseService.findCourse(course,pageNum,pageSize);
      if(pageInfo!=null){
         return "success";
      }else{
         return "failed";
      }
   }

   //查找所有的课程信息
   @PostMapping("/findAll/{pageNum}/{pageSize}")
   public String findAllCourse(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize){
      int res=-1;
      PageInfo<Course> pageInfo =courseService.findAllCourse(pageNum,pageSize);
      if(pageInfo!=null){
         return "success";
      }else{
         return "failed";
      }
   }


   //查找指定课程下的所有的试卷的信息
   @PostMapping("/findCourseAndPaperById/{id}")
   public String findCourseAndPaperById(@PathVariable("id")Long id){
      int res=-1;
      Course course=courseService.findCourseAndPaperById(id);
      if(course!=null){
         return "success";
      }else{
         return "failed!";
      }
   }
}
