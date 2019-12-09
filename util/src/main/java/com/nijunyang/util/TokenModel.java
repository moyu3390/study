package com.nijunyang.util;

import java.io.Serializable;

/**
 * Description:
 * Created by nijunyang on 2019/11/7 18:00
 */
public class TokenModel implements Serializable {

    private static final long serialVersionUID = 1425280877903876419L;

    private String accessToken;
    private String tokenType;
    private String expiresIn;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }
}
