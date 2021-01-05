package com.codewinter.backgroundmanagement.controller;

import com.alibaba.fastjson.JSONArray;
import com.codewinter.backgroundmanagement.entity.Role;
import com.codewinter.backgroundmanagement.service.RoleService;
import com.codewinter.backgroundmanagement.tool.CommonReturnType;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;



@Controller
@RequestMapping("/role")
public class RoleController {
   @Autowired
   RoleService roleService;

   @RequestMapping("/add")
   public String updateRole(Role role){
      Long res=roleService.save(role);

      return "";
   }

   @RequestMapping("/get/{pageNum}/{pageSize}")
   public ModelAndView getAllRole(@PathVariable("pageNum")int pageNum, @PathVariable("pageSize")int pageSize){
      PageInfo<Role> pageInfo=roleService.getAllRoles(pageNum,pageSize);

      ModelAndView modelAndView=new ModelAndView();
      modelAndView.setViewName("role-list");
      modelAndView.addObject("RoleList",pageInfo);
      return modelAndView;
   }

   @RequestMapping(value="/update")
   @ResponseBody
   public CommonReturnType updateSelectedRole(@RequestParam("operation") String operatorType,@RequestParam("data") String data){

      //json字符串转数组
      JSONArray jsonArray = JSONArray.parseArray(data);
      List<Long> ids = jsonArray.toJavaList(Long.class);

      //判断是否是启用状态操作
      if("status-enable".equals(operatorType)){
         //获取role的id
         for(int i=0;i<ids.size();i++){
            Role role=new Role();
            role.setId(ids.get(i));
            role.setStatus(1);
            roleService.updateRoleyId(role);
         }
         //判断是否禁用操作
      }if("status-disable".equals(operatorType)){
         //获取role的id
         for(int i=0;i<ids.size();i++){
            Role role=new Role();
            role.setId(ids.get(i));
            role.setStatus(0);
            roleService.updateRoleyId(role);
         }
      }

      //设置返回结果
      CommonReturnType commonReturnType=new CommonReturnType();
      commonReturnType.setStatus("success");
      commonReturnType.setData("update role status ok!");
      return commonReturnType;
   }

   @RequestMapping("/delete")
   @ResponseBody
   public CommonReturnType deleteSelectedRole(@RequestParam("data") String data){

      //json字符串转数组
      JSONArray jsonArray = JSONArray.parseArray(data);
      List<Long> ids = jsonArray.toJavaList(Long.class);

      //根据id删除角色
      roleService.deleteRoleByIds(ids);

      //设置返回结果
      CommonReturnType commonReturnType=new CommonReturnType();
      commonReturnType.setStatus("success");
      commonReturnType.setData("update role status ok!");
      return commonReturnType;
   }

   @GetMapping("/get/{id}")
   public ModelAndView getRoleById(@PathVariable("id")Long id){

      //此接口建议从缓存中获取权限信息（使用shiro将权限信息录入缓存中）
      ModelAndView modelAndView=new ModelAndView();
      if(id!=null&&id>=0){
         System.out.println("error: id is null");
         Role role=roleService.findRoleAndPermById(id);

         if(role==null){
            System.out.println("error: role is null");
            modelAndView.setViewName("error");
            return modelAndView;
         }
         System.out.println(role);
         modelAndView.addObject("role",role);
         modelAndView.setViewName("role-detail");
         return modelAndView;
      }else{
         modelAndView.setViewName("error");
         return modelAndView;
      }
   }

   @GetMapping("/getAllRoles")
   @ResponseBody
   public CommonReturnType getAllRoles(){
      PageInfo<Role> rolePageInfo=roleService.getAllRoles(1,20);

      CommonReturnType commonReturnType=new CommonReturnType();
      commonReturnType.setStatus("200");
      commonReturnType.setData(rolePageInfo.getList());
      return commonReturnType;
   }


}
