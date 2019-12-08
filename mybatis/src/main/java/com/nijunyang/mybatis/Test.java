package com.nijunyang.mybatis;

import com.nijunyang.mybatis.mapper.UserMapper;
import com.nijunyang.mybatis.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author: create by nijunyang
 * @date:2019/7/3
 */
public class Test {


    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession(true);
        UserMapper mapper = session.getMapper(UserMapper.class);
        User user = new User();
        user.setUserName("王五");
        user.setUserCard("5111292");
        user.setAge(17);
        user.setCity("成都");
        mapper.insertUser(user);

    }



}
