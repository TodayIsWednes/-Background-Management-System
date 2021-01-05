package com.codewinter.backgroundmanagement.dao;


import com.codewinter.backgroundmanagement.entity.Perm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PermDao {

   Perm findPermById(Long id);

   List<Perm> findPermByName(String permName);

   List<Perm> findPermByRoleId(Long roleId);

   List<Perm> findPerm(Perm perm);

   List<Perm> findAllPerms();

   int save(Perm perm);

   int deletePerm(Perm perm);

   int updatePermById(Perm perm);
}
