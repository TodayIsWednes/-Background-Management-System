package com.codewinter.backgroundmanagement.controller;


import com.codewinter.backgroundmanagement.entity.Paper;
import com.codewinter.backgroundmanagement.service.PaperService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/paper")
public class PaperController {

   @Autowired
   PaperService paperService;

   //添加新试卷
   @RequestMapping("/add")
   public String addNewPaper(Paper paper){
      int res=-1;
      res=paperService.save(paper);
      if(res!=-1){
         return "success";
      }else{
         return "failed";
      }
   }

   //删除试卷
   @RequestMapping("/delete/{id}")
   public String deletePaper(@PathVariable("id") Long id){
      int res=-1;
      Paper paper=new Paper();
      paper.setId(id);
      res=paperService.deletePaper(paper);
      if(res!=-1){
         return "success";
      }else{
         return "fail";
      }
   }

   //修改试卷
   @RequestMapping("/update/{id}")
   public String updatePaperById(Paper paper,@PathVariable("id") Long id){
      int res=-1;
      paper.setId(id);
      res=paperService.updatePaperById(paper);
      if(res!=-1){
         return "success";
      }else{
         return "failed";
      }

   }


   //查找试卷-条件查找
   @RequestMapping("/find/{pageNum}/{pageSize}")
   public String findPapers(Paper paper,@PathVariable("pageNum") int pageNum,@PathVariable("pageSize") int pageSize){
      PageInfo<Paper> pageInfo=paperService.findPaper(paper,pageNum,pageSize);
      if(pageInfo!=null){
         return "sucess";
      }else{
         return "fail";
      }

   }
   //查找所有试卷
   @RequestMapping("/findAll/{pageNum}/{pageSize}")
   public String findAllPapers(@PathVariable("pageNum") int pageNum,@PathVariable("pageSize") int pageSize){
      PageInfo<Paper> pageInfo=paperService.findAllPapers(pageNum,pageSize);
      if(pageInfo!=null){
         return "sucess";
      }else{
         return "fail";
      }
   }

   //查找指定试卷-下的所有试题信息
   @RequestMapping("/findPaperAndQuestionById/{id}")
   public String findPaperAndQuestionById(@PathVariable("id")Long id){
      Paper paper=paperService.findPaperAndQuestionById(id);
      if(paper!=null){
         return "sucess";
      }else{
         return "fail";
      }
   }

   //试卷添加试题-选择题-是否到题型上限
   @RequestMapping("/addQuestion/{paperid}/{questionid}/")
   public String addQuestion(@PathVariable("paperid")Long paperId,
                                   @PathVariable("questionid")Long questionId,
                                   int score,int order){
      //paperService.addQuestion(Long paperId,Long questionId,int score);

      return "ok";
   }

   //试卷添加试题-填空题

   //试卷添加试题-简答题

}
