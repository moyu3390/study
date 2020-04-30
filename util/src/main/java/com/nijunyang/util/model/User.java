package com.nijunyang.util.model;

/**
 * Description:
 * Created by nijunyang on 2020/4/16 16:00
 */

public class User {

    private int id;
    private int age;

    public User() {
    }

    public User(int id, int age) {
        this.id = id;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "id="+id+",age="+age;
    }
}
