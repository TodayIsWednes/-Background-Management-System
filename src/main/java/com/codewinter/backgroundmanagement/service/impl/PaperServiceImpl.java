package com.codewinter.backgroundmanagement.service.impl;

import com.codewinter.backgroundmanagement.dao.*;
import com.codewinter.backgroundmanagement.entity.*;
import com.codewinter.backgroundmanagement.service.PaperService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaperServiceImpl implements PaperService {

   @Autowired
   PaperDao paperDao;

   @Autowired
   QuestionDao questionDao;
   @Autowired
   CourseDao courseDao;


   @Override
   public Paper findPaperById(Long id) {
      return paperDao.findPaperById(id);
   }

   @Override
   public Paper findPaperByName(String paperName) {
      return findPaperByName(paperName);
   }

   @Override
   public PageInfo<Paper> findPaper(Paper paper,int pageNum,int pageSize) {
      PageHelper.offsetPage(pageNum,pageSize);
      List<Paper> list= paperDao.findPaper(paper);
      PageInfo<Paper> pageInfo=new PageInfo<>();
      pageInfo.setList(list);
      return pageInfo;
   }

   @Override
   public PageInfo<Paper> getAllPapers(int pageNum,int pageSize) {
      PageHelper.offsetPage(pageNum,pageSize);
      List<Paper> list=paperDao.getAllPapers();
      PageInfo<Paper> pageInfo=new PageInfo<>();
      pageInfo.setList(list);
      return pageInfo;
   }

   @Override
   public int save(Paper paper) {
      return paperDao.save(paper);
   }

   @Override
   public int deletePaper(Paper paper) {
      return paperDao.deletePaper(paper);
   }

   @Override
   public int updatePaperById(Paper paper) {
      return paperDao.updatePaperById(paper);
   }

   @Override
   public void addQuestionForPaper(Long paperId, Long questionId, int score) {

   }

   /*
   * 涉及操作多张表，将操作纳入spring的事务管理中
   * */
   @Override
   @Transactional
   public Paper findPaperAndQuestionById(Long id) {
      Paper paper=new Paper();
      return paper;
   }
}
