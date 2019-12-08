package com.nijunyang.redis;

import redis.clients.jedis.Jedis;

/**
 * @author: create by nijunyang
 * @date:2019/7/1
 */
public class Test {

    public static void main(String[] args)
    {
        Jedis jedis = new Jedis("192.168.0.66",6379);

        //set 字符串
        jedis.set("name", "xiaohuaasdasd1212121");
        jedis.set("stu", "xiaohuaasdasd1212121");
        //获取字符串
        String value = jedis.get("name");
        System.out.println(value);

        //获取匹配的所有key
        System.out.println(jedis.keys("*"));
        //删除key
        jedis.del("name");
        System.out.println(jedis.keys("*"));
        //判断key是否存在
        jedis.exists("name");
        //查看key对应的value类型
        System.out.println(jedis.type("stu"));


        //清空所有的数据
//        jedis.flushDB();
        jedis.close();



    }
}
