package com.nijunyang.mysql.model;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.util.JSONPObject;
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

    @JsonFormat(timezone = "GMT+8")
    @Schema(description = "开始时间",example = "yyyy-MM-dd HH:mm:ss")
    private Instant createTime;

    public static void main(String[] args){
        User user = new User();
        user.userName = "zhangsan";
        user.userCard = "511129";
        user.age = 18;
        user.createTime = Instant.now();
//        long epochSecond = user.createTime.getEpochSecond();
//        System.out.println(epochSecond);
//        System.out.println(user.createTime.plusSeconds(60*60*24*2).getEpochSecond());
//        System.out.println(System.currentTimeMillis());

        String userStr = JSON.toJSONString(user);
        System.out.println(userStr);
        User user1 = JSON.parseObject(userStr, User.class);
        System.out.println(user1.getCreateTime());
//        System.out.println(JsonUtils.write2JsonString(user));
    }

}
