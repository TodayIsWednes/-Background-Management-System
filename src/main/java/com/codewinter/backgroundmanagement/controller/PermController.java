package com.codewinter.backgroundmanagement.controller;

import cn.hutool.core.date.DateUtil;
import com.codewinter.backgroundmanagement.entity.Perm;
import com.codewinter.backgroundmanagement.service.PermService;
import com.codewinter.backgroundmanagement.tool.CommonReturnType;
import com.codewinter.backgroundmanagement.vo.PermVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/perm")
public class PermController {
   @Autowired
   PermService permService;

   @RequestMapping("/get")
   @ResponseBody
   public CommonReturnType getAllPerms(){

      CommonReturnType commonReturnType=new CommonReturnType();

      //建议添加shrio后从缓存中获取权限信息

         List<Perm> permList=permService.findAllPerms();
      System.out.println(permList);
         List<PermVo> permVoFirstList=new ArrayList<>();
         List<PermVo> permVoSecondList=new ArrayList<>();
         List<PermVo> permVoThirdList=new ArrayList<>();

         //获取一级菜单
         for(int i=0;i<permList.size();i++){
            PermVo permVo=new PermVo();
            if(permList.get(i).getLevel()==1){
               permVo.setPropertyFromPerm(permList.get(i));
               permVoFirstList.add(permVo);
            }
            if(permList.get(i).getLevel()==2){

               permVo.setPropertyFromPerm(permList.get(i));
               permVoSecondList.add(permVo);

            }
            if(permList.get(i).getLevel()==3){
               permVo.setPropertyFromPerm(permList.get(i));
               permVoThirdList.add(permVo);
            }
         }

         //建立三级级菜单与二级菜单的从属关系
         for(int j=0;j<permVoThirdList.size();j++){
            for(int i=0;i<permVoSecondList.size();i++){
               if(permVoThirdList.get(j).getParentId()==permVoSecondList.get(i).getId()){

                  permVoSecondList.get(i).getChildren().add(permVoThirdList.get(j));
               }
            }
         }
         //建立二级级菜单与一级菜单的从属关系
         for(int j=0;j<permVoSecondList.size();j++){
            for(int i=0;i<permVoFirstList.size();i++){
               if(permVoSecondList.get(j).getParentId()==permVoFirstList.get(i).getId()){
                  permVoFirstList.get(i).getChildren().add(permVoSecondList.get(j));
               }
            }
         }
         commonReturnType.setStatus("500");
         commonReturnType.setData(permVoFirstList);
         return commonReturnType;
   }

   @RequestMapping("/getByRoleId/{roleId}")
   public CommonReturnType getPermsByRoleId(@PathVariable("roleId")Long roleId){
      List<Perm> permList=permService.findPermByRoleId(roleId);
      CommonReturnType commonReturnType=new CommonReturnType();
      commonReturnType.setStatus("500");
      commonReturnType.setData(permList);
      return commonReturnType;
   }

   @RequestMapping("/add")
   @ResponseBody
   public CommonReturnType addPerm(@RequestBody Perm perm){
      System.out.println(perm);
      CommonReturnType commonReturnType=new CommonReturnType();
      if(null==perm.getPermName()|"".equals(perm.getPermName())){
         commonReturnType.setStatus("500");
      }else{
         perm.setAddTime(DateUtil.date());
         perm.setUpdateTime(DateUtil.date());
         System.out.println(perm);
         permService.save(perm);
         commonReturnType.setStatus("200");
      }

      return commonReturnType;

   }


   @RequestMapping("/findPerms")
   @ResponseBody
   public CommonReturnType findPermsByTag(@RequestBody Perm perm ){

      List<Perm> permList=permService.findPerm(perm);
      List<PermVo> permVoList=new ArrayList<>();
      for(int i=0;i<permList.size();i++){
         PermVo permVo=new PermVo();
         permVo.setPropertyFromPerm(permList.get(i));
         permVoList.add(permVo);
      }

      CommonReturnType commonReturnType=new CommonReturnType();
      commonReturnType.setStatus("200");
      commonReturnType.setData(permVoList);

      return commonReturnType;
   }

   @RequestMapping("/view")
   public ModelAndView permView(){
      ModelAndView modelAndView=new ModelAndView();
      modelAndView.setViewName("perm-list");
      return modelAndView;
   }
}
