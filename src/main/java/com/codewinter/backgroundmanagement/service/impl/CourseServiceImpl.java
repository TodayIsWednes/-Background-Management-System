package com.codewinter.backgroundmanagement.service.impl;

import com.codewinter.backgroundmanagement.dao.CourseDao;
import com.codewinter.backgroundmanagement.entity.Course;
import com.codewinter.backgroundmanagement.service.CourseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService
{

   @Autowired
   CourseDao courseDao;

   @Override
   public Course findCourseById(Long id) {
      return courseDao.findCourseById(id);
   }

   @Override
   public PageInfo<Course> findCourseByName(String courseName,int pageNum,int pageSize) {
      PageHelper.offsetPage(pageNum,pageSize);
      List<Course> list=courseDao.findCourseByName(courseName);
      PageInfo<Course> pageInfo=new PageInfo<>();
      pageInfo.setList(list);
      return pageInfo;
   }

   @Override
   public PageInfo<Course> findCourse(Course course,int pageNum,int pageSize) {
      PageHelper.offsetPage(pageNum,pageSize);
      List<Course> list=courseDao.findCourse(course);
      PageInfo<Course> pageInfo=new PageInfo<>();
      pageInfo.setList(list);
      return pageInfo;

   }

   @Override
   public PageInfo<Course> findAllCourse(int pageNum, int pageSize) {
      PageHelper.offsetPage(pageNum,pageSize);
      List<Course> list=courseDao.findAllCourse();
      PageInfo<Course> pageInfo=new PageInfo<>();
      pageInfo.setList(list);
      return pageInfo;
   }

   @Override
   public Long save(Course course) {
      return courseDao.save(course);
   }

   @Override
   public int deleteCourse(Course course) {
      return courseDao.deleteCourse(course);
   }

   @Override
   public int updateCourseById(Course course) {
      return courseDao.updateCourseById(course);
   }

   @Override
   public Course findCourseAndPaperById(Long id) {
      return courseDao.findCourseAndPaperById(id);
   }
}
