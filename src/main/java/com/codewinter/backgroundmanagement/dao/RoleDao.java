package com.codewinter.backgroundmanagement.dao;


import com.codewinter.backgroundmanagement.entity.Role;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

@Mapper
public interface RoleDao {
   Role findRoleAndPermById(Long id);

   List<Role> findRoleByName(String roleName);

   Role findRoleById(Long id);

   List<Role> findRole(Role role);

   List<Role> findRolesByUserId(Long id);

   List<Role> getAllRoles();

   Long save(Role role);

   int deleteRole(Role role);

   int deleteRoleByIds(List<Long> idList);

   int updateRoleyId(Role role);

}
