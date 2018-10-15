package com.learn.chapter2.dao;

import com.learn.chapter2.po.Role;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;
import java.util.Set;

//todo 注意各个方法的返回值，很有意思。
public interface RoleMapper {

    //根据查询id 来获取Role对象。
    Role getRole(Long id);


    //根据id删除一个角色。
    int deleteRole(Long id);

    //插入一个角色。
     int insertRole(Role role);

     Set<Role> getRolesByRoleNameLike(String likeKey);

     //把单条记录封装为map
     Map<String,Role>  getRoleByIdReturnMap(Long id);


     @MapKey("id") //告诉mybatis ,哪个属性作为map的key.
     //把多条记录封装为map
    Map<Long,Role> getRolesByLikeReturnMap(String likeString);

    @MapKey("roleName")
    Map<String,Role> getRolesByLikeReturnMap2(String likeString);
 }
