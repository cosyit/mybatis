package com.learn.chapter2.dao;

import com.learn.chapter2.po.Role;

//todo 注意各个方法的返回值，很有意思。
public interface RoleMapper {

    //根据查询id 来获取Role对象。
    Role getRole(Long id);


    //根据id删除一个角色。
    int deleteRole(Long id);

    //插入一个角色。
     int insertRole(Role role);
}
