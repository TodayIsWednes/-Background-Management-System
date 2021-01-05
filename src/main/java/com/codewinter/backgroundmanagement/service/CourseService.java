package com.codewinter.backgroundmanagement.service;

import com.codewinter.backgroundmanagement.entity.Course;
import com.github.pagehelper.PageInfo;


public interface CourseService {

   Course findCourseById(Long id);

   PageInfo<Course> findCourseByName(String courseName,int pageNum,int pageSize);

   PageInfo<Course>  findCourse(Course course,int pageNum,int pageSize);

   PageInfo<Course> findAllCourse(int pageNum,int pageSize);

   Long save(Course course);

   int deleteCourse(Course course);

   int updateCourseById(Course course);

   Course findCourseAndPaperById(Long id);

}
