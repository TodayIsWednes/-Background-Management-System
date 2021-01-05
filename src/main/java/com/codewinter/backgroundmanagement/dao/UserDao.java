package com.codewinter.backgroundmanagement.dao;

import com.codewinter.backgroundmanagement.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface UserDao {

   User findUserByName(String username);

   User findUserById(Long id);

   List<User> findUser(User user);
   /**
    * 1.查询用户和与之管理的角色信息
    * 2.查询用户和与之管理的权限信息
    */


   Long save(User user);

   List<User> getAllUsers();

   int deleteUser(User user);

   int deleteUserByIds(List<Long> idList);

   int updateUserById(User user);

   int deleteUserRoleByIds(Map<String, Object> params);

   int addRoleForUser(Long userId, Long roleId, Date createTime);
}


