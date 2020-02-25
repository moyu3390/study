package com.nijunyang.spring.model;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Description:
 * Created by nijunyang on 2020/2/23 10:53
 */
public class Teacher {

    @Autowired
    private Student student;
}
