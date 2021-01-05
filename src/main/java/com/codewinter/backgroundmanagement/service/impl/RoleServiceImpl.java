package com.codewinter.backgroundmanagement.service.impl;

import com.codewinter.backgroundmanagement.dao.RoleDao;

import com.codewinter.backgroundmanagement.entity.Role;
import com.codewinter.backgroundmanagement.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {

   @Autowired
   RoleDao roleDao;

   @Override
   public Role findRoleAndPermById(Long id) {
      return roleDao.findRoleAndPermById(id);
   }

   @Override
   public PageInfo<Role> findRoleByName(String roleName,int pageNum,int pageSize) {
      PageHelper.offsetPage(pageNum,pageSize);
      List<Role> roleList=roleDao.findRoleByName(roleName);
      PageInfo<Role> pageInfo=new PageInfo<>(roleList);
      pageInfo.setList(roleList);
      return pageInfo;
   }

   @Override
   public Role findRoleById(Long id) {
      return roleDao.findRoleById(id);
   }

   @Override
   public PageInfo<Role> findRole(Role role,int pageNum,int pageSize) {
      PageHelper.offsetPage(pageNum,pageSize);
      List<Role> roleList=roleDao.findRole(role);
      PageInfo<Role> pageInfo=new PageInfo<>(roleList);
      pageInfo.setList(roleList);
      return pageInfo;
   }

   @Override
   public List<Role> findRolesByUserId(Long id) {
      return roleDao.findRolesByUserId(id);
   }

   @Override
   public PageInfo<Role> getAllRoles(int pageNum,int pageSize) {
      PageHelper.startPage(pageNum,pageSize);
      List<Role> roleList=roleDao.getAllRoles();
      PageInfo<Role> pageInfo=new PageInfo<>(roleList);
      pageInfo.setList(roleList);
      return pageInfo;
   }

   @Override
   public Long save(Role role) {
      return roleDao.save(role);
   }

   @Override
   public int deleteRole(Role role) {
      return roleDao.deleteRole(role);
   }

   @Override
   public int deleteRoleByIds(List<Long> idList) {
      return roleDao.deleteRoleByIds(idList);
   }

   @Override
   public int updateRoleyId(Role role) {
      return roleDao.updateRoleyId(role);
   }
}
