package com.learn.chapter2.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * 我们在之前说过，我们通过SqlSessionFactoryBuilder读取配置Configuration对象，来生产出一个单例的SqlSessionFactory.
 */
public class SqlSessionFactoryUtil {
    //sqlSessionFactory对象
    private static SqlSessionFactory sqlSessionFactory = null;

    //类线程锁
    private static final Class CLASS_LOCK = SqlSessionFactoryUtil.class;

    //私有化Util的构造器。就是为了不让你new.
    private SqlSessionFactoryUtil(){}


    //我们这里只要搞一个单例的从配置中加载出来的SqlSessionFactory对象即可。
    // 我们希望此方法返回的SqlSessionFactory，对于数据库而言只有一个实例
    public static SqlSessionFactory initSqlSessionFactory(){
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
             inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //防止web服务器，或者socket请求等多线程环境中，多次请求初始化对象造成的不唯一。
        synchronized (CLASS_LOCK){
            if(sqlSessionFactory == null){
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            }
        }
        return sqlSessionFactory;
    }


    /**
     *  todo 逻辑没有毛病很，缜密。打开sqlSession ，这里的if写法还是比较精妙的。值得学习一番。
     */

    public static SqlSession openSqlSession(){

        //todo 你看这里,如果类域中的 sqlSessionFactory 为null ,他会自动给本类的sqlSessionFactory赋值。
        if(sqlSessionFactory == null){
            initSqlSessionFactory();
        }

        //如果本类中的，不为null ,就直接走这里。如果为null,先从if中走一下，再走这里，更好，不会是null.method()。
        return sqlSessionFactory.openSession();
    }


}
