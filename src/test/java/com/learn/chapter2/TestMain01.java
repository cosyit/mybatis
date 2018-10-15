package com.learn.chapter2;

import com.learn.chapter2.dao.RoleMapper;
import com.learn.chapter2.po.Role;
import com.learn.chapter2.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Set;

public class TestMain01 {

    public static void main(String[] args) {

        SqlSession sqlSession = null;

        sqlSession = SqlSessionFactoryUtil.openSqlSession();
        RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);

//
//       Role role1 =  roleMapper.getRole(1L);
//
//        System.out.println(role1.getId() +" , "+ role1.getNote() +" , "+ role1.getRoleName());
//
//
//        Role role2 = new Role();
//        role2.setNote("test insert method");
//        role2.setRoleName("test role");
//        roleMapper.insertRole(role2);
//        sqlSession.commit();
//
//        roleMapper.deleteRole(3L);
//
//        sqlSession.commit();


        Set<Role> list = roleMapper.getRolesByRoleNameLike("%role%");

        System.out.println(list);

        for(Role role:list){
            System.out.println(role);
        }

    }
}
