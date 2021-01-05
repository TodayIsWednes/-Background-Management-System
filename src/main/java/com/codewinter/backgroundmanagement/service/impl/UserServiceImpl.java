package com.codewinter.backgroundmanagement.service.impl;

import cn.hutool.core.date.DateUtil;
import com.codewinter.backgroundmanagement.dao.UserDao;
import com.codewinter.backgroundmanagement.entity.User;
import com.codewinter.backgroundmanagement.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl  implements UserService {
   @Autowired
   private UserDao userDao;


   @Override
   public User findUserByUserName(String username) {
      return userDao.findUserByName(username);
   }

   @Override
   public User findUserById(Long id) {
      return userDao.findUserById(id);
   }

   @Override
   public List<User> findUser(User user) {
      return userDao.findUser(user);
   }

   @Override
   public Long  save(User user) {
      return userDao.save(user);
   }

   @Override
   public PageInfo<User> getAllUsers(int pageNum,int pageSize) {
      PageHelper.startPage(pageNum,pageSize);
      List<User> userList=userDao.getAllUsers();
      PageInfo<User> pagInfo=new PageInfo<>(userList);
      return pagInfo;
   }

   @Override
   public int deleteUser(User user) {
      return userDao.deleteUser(user);
   }

   @Override
   public int deleteUserByIds(List<Long> idList) {
      return userDao.deleteUserByIds(idList);
   }

   @Override
   public int updateUserById(User user) {
      return userDao.updateUserById(user);
   }

   @Override
   public int deleteUserRoleRelationship(Map<String, Object> params) {
      return userDao.deleteUserRoleByIds(params);
   }

   @Override
   public int addRoleForUser(Long userId, Long roleId) {
      Date createTime = DateUtil.date();
      return userDao.addRoleForUser(userId,roleId,createTime);
   }
}
