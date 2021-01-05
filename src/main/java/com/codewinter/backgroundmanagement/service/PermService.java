package com.codewinter.backgroundmanagement.service;

import com.codewinter.backgroundmanagement.entity.Perm;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface PermService {
   Perm findPermById(Long id);

   List<Perm> findPermByName(String permName);

   List<Perm> findPermByRoleId(Long roleId);

   List<Perm>  findPerm(Perm perm);

   List<Perm>  findAllPerms();

   int save(Perm perm);

   int deletePerm(Perm perm);

   int updatePermById(Perm perm);
}
