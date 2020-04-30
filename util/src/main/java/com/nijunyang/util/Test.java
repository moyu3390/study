package com.nijunyang.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nijunyang.util.model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Description:
 * Created by nijunyang on 2020/4/16 16:00
 */
public class Test {

    public static void main(String[] args) throws ParseException {
//        User user1 = new User(1,1);
//        User user2 = new User(2,2);
//        User user3 = new User(3,3);
//        User user4 = new User(4,4);
//        User user5 = new User(5,5);
//        User user6 = new User(6,6);
//        List<User> list = new ArrayList<>();
//        list.add(user1);
//        list.add(user2);
//        list.add(user3);
//        list.add(user4);
//        list.add(user5);
//        list.add(user6);
//        String s = JsonUtils.write2JsonString(list);
//        List<User> userList = JsonUtils.readJson2EntityList(s, User.class);
//        System.out.println(userList);

//        String time = "2020-04-29T14:47:51.003";

        String time = "2020-04-29T06:33:50.334Z";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'.'SSS'Z'");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date data = df.parse(time);
        System.out.println(data);

//        LocalDateTime localDateTime = LocalDateTime.now();
//        System.out.println(localDateTime);
    }
}
