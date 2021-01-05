package com.codewinter.backgroundmanagement.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.codewinter.backgroundmanagement.entity.Role;
import com.codewinter.backgroundmanagement.entity.User;
import com.codewinter.backgroundmanagement.service.RoleService;
import com.codewinter.backgroundmanagement.service.UserService;
import com.codewinter.backgroundmanagement.tool.CommonReturnType;
import com.codewinter.backgroundmanagement.tool.SaltUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
   @Autowired
   UserService userService;

   @Autowired
   RoleService roleService;

   @PostMapping("/login")
   public String login(String username, String password, HttpSession httpSession, Model model){

      System.out.println("login - post");
      if("".equals(username)||"".equals(password)){
         return "用户名或密码不能为空";
      }

      User user=userService.findUserByUserName(username);
      if(user.getPassWord().equals(password)){
         user.setPassWord(null);
         //model.addAttribute("user",user);
         httpSession.setAttribute("user",user);
         return "index";
      }else{
       return "error-400";
      }

   }

   @PostMapping("/register")
   public String postRegister(User user,HttpSession session) throws ParseException {
      Long res=-1l;
      if("".equals(user.getPassWord())||"".equals(user.getUserName()))
      {
         return "register";
      }

      Date createTime=new Date();
      System.out.println(createTime.toString());
      SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
      String nowTime= simpleDateFormat.format(createTime);
      Date time= simpleDateFormat.parse(nowTime);

      //获取加密盐
      String salt= SaltUtils.getSalt(8);
      //使用加密盐和加密算计获取新密码
      String newpassword="";

      user.setCreateTime(time);
      user.setSalt(salt);
      user.setStatus(0);

      res=userService.save(user);
      if(res!=-1){
         return "/login";
      }else{
         return "/register";
      }
   }


   @PostMapping("/deleteuser")
   @ResponseBody
   public String deleteUser(User user){
      int res=-1;
      res=userService.deleteUser(user);
      return "success:"+res;
   }


   @GetMapping("/view")
   @ResponseBody
   public ModelAndView getAllUser(){

      ModelAndView modelAndView=new ModelAndView();
      modelAndView.setViewName("user-list");

      return modelAndView;
   }

   @RequestMapping("/get/{pageNum}/{pageSize}")
   @ResponseBody
   public CommonReturnType getAllUsers(@PathVariable(value="pageNum") int pageNum, @PathVariable("pageSize") int pageSize){
      PageInfo<User> pageInfo=userService.getAllUsers(pageNum,pageSize);
      CommonReturnType commonReturnType=new CommonReturnType();
      commonReturnType.setStatus("200");
      commonReturnType.setData(pageInfo);
      return commonReturnType;
   }

   @RequestMapping("/get/{userId}")
   public CommonReturnType getUserById(@PathVariable("id")Long id){
      User user=userService.findUserById(id);
      CommonReturnType commonReturnType=new CommonReturnType();
      if(user!=null){

         commonReturnType.setStatus("200");
         commonReturnType.setData(user);
      }else{
         commonReturnType.setStatus("200");
         commonReturnType.setData("user not found!");
      }

      return commonReturnType;
   }

   @RequestMapping("/delete/{id}")
   @ResponseBody
   public CommonReturnType deleteUserById(@PathVariable("id")Long id){
      User user=new User();
      user.setId(id);
      System.out.println(user);
      int res=userService.deleteUser(user);
      CommonReturnType commonReturnType=new CommonReturnType();
      commonReturnType.setStatus("200");
      commonReturnType.setData("delete "+res);
      return commonReturnType;
   }


   @RequestMapping("/updateUsers")
   @ResponseBody
   public CommonReturnType updateUsers(@RequestBody JSONObject jsonParam){
      String userData=jsonParam.get("ids").toString();
      String opType=jsonParam.get("type").toString();

      JSONArray jsonArray = JSONArray.parseArray(userData);
      List<Long> ids = jsonArray.toJavaList(Long.class);

      if("status-enable".equals(opType)){
         for(int i=0;i<ids.size();i++){
            User user=new User();
            user.setId(ids.get(i));
            user.setStatus(1);
            userService.updateUserById(user);
         }
      }else if("status-disable".equals(opType)){

      }

      CommonReturnType commonReturnType=new CommonReturnType();
      commonReturnType.setStatus("200");
      commonReturnType.setData("OK OK!");

      return commonReturnType;
   }

   @RequestMapping("/edit/{id}")
   public ModelAndView editUserById(@PathVariable("id")Long id){
         ModelAndView modelAndView=new ModelAndView();
         modelAndView.setViewName("user-detail");
         User user=userService.findUserById(id);

         if(user!=null){
               modelAndView.addObject("user",user);
                List<Role> roleList=roleService.findRolesByUserId(id);
                modelAndView.addObject("roleList",roleList);
         }
         return modelAndView;
   }





   @RequestMapping("/deleteRelation")
   @ResponseBody
   public CommonReturnType deleteUserRoleRelationship(@RequestParam("data") String data,@RequestParam("userId")Long userId){
      //json字符串转数组
      JSONArray jsonArray = JSONArray.parseArray(data);
      List<Long> ids = jsonArray.toJavaList(Long.class);


      Map<String, Object> params = new HashMap<String, Object>(2);
      params.put("ids", ids);
      params.put("userId", userId);

      int res=userService.deleteUserRoleRelationship(params);

      CommonReturnType commonReturnType=new CommonReturnType();
      commonReturnType.setStatus("200");
      commonReturnType.setData(res+" relationship delete!");
      return commonReturnType;
   }

   @RequestMapping("/getUserRole")
   @ResponseBody
   public CommonReturnType getRoleByUserId(@RequestParam("userId")Long userId){
      List<Role> roleList=roleService.findRolesByUserId(userId);
      CommonReturnType commonReturnType=new CommonReturnType();
      commonReturnType.setStatus("200");
      commonReturnType.setData(roleList);
      return commonReturnType;
   }

   @RequestMapping("/addRoleForUser")
   @ResponseBody
   public CommonReturnType addRoleForUser(@RequestParam("userId")Long userId,@RequestParam("roleId")Long roleId){

     int res= userService.addRoleForUser(userId,roleId);
      CommonReturnType commonReturnType=new CommonReturnType();
      commonReturnType.setStatus("200");
      commonReturnType.setData("add" +res+"role for user ok!");
      return commonReturnType;
   }

   @GetMapping("/login")
   public String login() {
      System.out.println("user");
      return "login";
   }

   @RequestMapping("/update")
   @ResponseBody
   public CommonReturnType updateUserById(@RequestBody User user){

      //将提交的密码与新盐加密
      String salt=SaltUtils.getSalt(8);
      user.setSalt(salt);
      //保存用户信息
      int res=1; //res=-2
      // res=userService.updateUserById(user);
      CommonReturnType commonReturnType=new CommonReturnType();
      if(res==1){
         commonReturnType.setStatus("200");
         commonReturnType.setData(" update user message ok!");
         return commonReturnType;
      }else{
         commonReturnType.setStatus("500");
         commonReturnType.setData(" update user message failed!");
         return commonReturnType;
      }
   }


   @GetMapping("/register")
   public String register(){
      return "registration";
   }
}
