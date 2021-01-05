package com.codewinter.backgroundmanagement.service;

import com.codewinter.backgroundmanagement.entity.User;

import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface UserService {

   User findUserByUserName(String username);
   User findUserById(Long id);

   List<User> findUser(User user);



   Long save(User user);

   PageInfo<User> getAllUsers(int pageNum,int pageSize);

   int deleteUser(User user);

   int deleteUserByIds(List<Long> idList);

   int updateUserById(User user);

   int deleteUserRoleRelationship(Map<String, Object> params);

   int addRoleForUser(Long userId, Long roleId);
}
