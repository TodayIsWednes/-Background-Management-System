package com.codewinter.backgroundmanagement.service.impl;

import com.codewinter.backgroundmanagement.dao.PermDao;
import com.codewinter.backgroundmanagement.entity.Perm;
import com.codewinter.backgroundmanagement.service.PermService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermServiceImpl implements PermService {
   @Autowired
   PermDao permDao;
   @Override
   public Perm findPermById(Long id) {
      return permDao.findPermById(id);
   }
   @Override
   public List<Perm> findPermByName(String permName) {
      List<Perm> list=permDao.findPermByName(permName);
      return list;
   }

   @Override
   public List<Perm> findPermByRoleId(Long roleId) {
      return permDao.findPermByRoleId(roleId);
   }

   @Override
   public  List<Perm>findPerm(Perm perm) {
      List<Perm> list=permDao.findPerm(perm);
      return list;
   }

   @Override
   public  List<Perm>findAllPerms() {
      List<Perm> list=permDao.findAllPerms();
      return list;
   }

   @Override
   public int save(Perm perm) {
      return permDao.save(perm);
   }

   @Override
   public int deletePerm(Perm perm) {
      return permDao.deletePerm(perm);
   }

   @Override
   public int updatePermById(Perm perm) {
      return permDao.updatePermById(perm);
   }
}
