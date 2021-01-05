package com.codewinter.backgroundmanagement.service;


import com.codewinter.backgroundmanagement.entity.Paper;
import com.codewinter.backgroundmanagement.entity.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface PaperService {


   Paper findPaperById(Long id);

   Paper findPaperByName(String paperName);

    PageInfo<Paper> findPaper(Paper paper,int pageNum,int pageSize);

   PageInfo<Paper> findAllPapers(int pageNum,int pageSize);

   int save(Paper paper);
   /**条件删除**/
   int deletePaper(Paper paper);

   int updatePaperById(Paper paper);

   void addQuestionForPaper(Long paperId,Long questionId,int score);

   Paper findPaperAndQuestionById(Long id);
}
