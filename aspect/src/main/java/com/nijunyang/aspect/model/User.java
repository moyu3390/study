package com.nijunyang.aspect.model;

import com.nijunyang.aspect.annotation.SensitiveInfo;

/**
 * Description: 
 * Created by nijunyang on 2019/12/23 16:43
 */
public class User {
    @SensitiveInfo
    String userName;
    @SensitiveInfo
    String phone;
    @SensitiveInfo
    String card;

    public User(String userName, String phone, String card) {
        this.userName = userName;
        this.phone = phone;
        this.card = card;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }
}
