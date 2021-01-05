package com.codewinter.backgroundmanagement.service;


import com.codewinter.backgroundmanagement.entity.Role;
import com.github.pagehelper.PageInfo;


import java.util.List;

public interface RoleService {

   Role findRoleAndPermById(Long id);

   PageInfo<Role> findRoleByName(String roleName,int pageNum, int pageSize);

   Role findRoleById(Long id);

   PageInfo<Role> findRole(Role role, int pageNum, int pageSize);

   List<Role> findRolesByUserId(Long id);

   PageInfo<Role> getAllRoles(int pageNum, int pageSize);

   Long save(Role role);

   int deleteRole(Role role);

   int deleteRoleByIds(List<Long> idList);

   int updateRoleyId(Role role);
}
