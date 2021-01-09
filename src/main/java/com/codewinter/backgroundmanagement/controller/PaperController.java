package com.codewinter.backgroundmanagement.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.codewinter.backgroundmanagement.entity.Paper;
import com.codewinter.backgroundmanagement.entity.Question;
import com.codewinter.backgroundmanagement.service.PaperService;
import com.codewinter.backgroundmanagement.service.QuestionService;
import com.codewinter.backgroundmanagement.tool.CommonReturnType;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.rmi.MarshalledObject;
import java.util.List;

@Controller
@RequestMapping("/paper")
public class PaperController {

   @Autowired
   PaperService paperService;

   @Autowired
   QuestionService questionService;

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
   @RequestMapping("/delete")
   @ResponseBody
   public CommonReturnType deletePaper(@RequestBody JSONObject data){

      JSONArray jsonArray=data.getJSONArray("ids");
      List<Long> ids = jsonArray.toJavaList(Long.class);

      Paper paper=null;
      int[] delPaperIds=new int[ids.size()];
      for(int i=0;i<ids.size();i++){
         paper=new Paper();
         paper.setId(ids.get(i));
         delPaperIds[i]=paperService.deletePaper(paper);
      }

       CommonReturnType commonReturnType=new CommonReturnType();
       commonReturnType.setStatus("200");
       commonReturnType.setData(delPaperIds);
       return commonReturnType;
   }

   //修改试卷
   @RequestMapping("/update")
   @ResponseBody
   public CommonReturnType updatePaperById(@RequestBody JSONObject data){
       String opType=data.getString("opType");
       JSONArray jsonArray=data.getJSONArray("ids");
       List<Long> longList=jsonArray.toJavaList(Long.class);

      CommonReturnType commonReturnType=new CommonReturnType();
      for(int i=0;i<longList.size();i++){
         if("enable-paper".equals(opType)){
            Paper paper=new Paper();
            paper.setId(longList.get(i));
            paper.setStatus(1); //启用
            paperService.updatePaperById(paper);
         }
         else if("disable-paper".equals(opType)){
            Paper paper=new Paper();
            paper.setId(longList.get(i));
            paper.setStatus(0);  //0为禁用
            paperService.updatePaperById(paper);
         }
      }
      commonReturnType.setStatus("200");
      commonReturnType.setData("Update paper's status successful!");

      return commonReturnType;
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
   @RequestMapping("/get/{pageNum}/{pageSize}")
   @ResponseBody
   public CommonReturnType getAllPapers(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize){
      PageInfo<Paper> pageInfo=paperService.getAllPapers(pageNum,pageSize);

      CommonReturnType commonReturnType=new CommonReturnType();
      commonReturnType.setStatus("200");;
      commonReturnType.setData(pageInfo);
      return commonReturnType;
   }


   @RequestMapping("/edit/{id}")
   public ModelAndView editPaperById(@PathVariable("id")Long id){
     ModelAndView modelAndView=new ModelAndView();

     //获取试卷的基本信息
      Paper paper=paperService.findPaperById(id);

      //获取试卷包含的题目
      List<Question> questionList=questionService.findQuestionsByPaperId(id);


      return modelAndView;
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


   @RequestMapping("/view")
   public ModelAndView  paperView(){
      ModelAndView modelAndView=new ModelAndView();
      modelAndView.setViewName("paper-list");
      return modelAndView;
   }


}
