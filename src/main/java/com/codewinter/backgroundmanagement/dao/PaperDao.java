package com.codewinter.backgroundmanagement.dao;

import com.codewinter.backgroundmanagement.entity.Paper;
import com.codewinter.backgroundmanagement.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PaperDao {
   Paper findPaperById(Long id);

   Paper findPaperByName(String paperName);

   /*条件查询*/
   List<Paper> findPaper(Paper paper);

   List<Paper> getAllPapers();

   int save(Paper paper);

   int deleteQuestionOfPaperByPaperId(Long paperId);

   int deletePaper(Paper paper);

   int updatePaperById(Paper paper);
}
