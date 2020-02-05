package com.nijunyang.mysql.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nijunyang.mysql.util.JsonUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;

/**
 * @author: create by nijunyang
 * @date:2019/7/3
 */
@Data
@Schema
public class User {

    @Schema(description = "商城图片",example = "http://xxxxx")
    private String userName;
    @Schema(description = "商城图片",example = "http://xxxxx")
    private String userCard;
    @Schema(description = "商城图片",example = "http://xxxxx")
    private int age;
    @Schema(description = "商城图片",example = "http://xxxxx")
    private String city;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    @Schema(description = "开始时间",example = "yyyy-MM-dd HH:mm:ss")
    private Instant createTime;

//    public static void main(String[] args){
//        User user = new User();
//        user.userName = "zhangsan";
//        user.userCard = "511129";
//        user.age = 18;
//        user.createTime = Instant.now();
//        System.out.println(JsonUtils.write2JsonString(user));
//    }

}
