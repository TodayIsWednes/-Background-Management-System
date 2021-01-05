package com.codewinter.backgroundmanagement.dao;

import com.codewinter.backgroundmanagement.entity.Course;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseDao {

   Course findCourseById(Long id);

   Course findCourseAndPaperById(Long id);

   List<Course> findCourseByName(String courseName);

   List<Course> findAllCourse();

   List<Course> findCourse(Course course);

   Long save(Course course);

   int deleteCourse(Course course);

   int updateCourseById(Course course);
}
