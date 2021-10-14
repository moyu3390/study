package com.nijunyang.util;

import java.io.Serializable;

/**
 * Description:
 * Created by nijunyang on 2019/11/7 18:00
 */
public class TokenModel implements Serializable {

    public static void main(String[] args) {
        System.out.println(waysToStep(10));
    }

    static int stair(int num) {
        if (num == 0) {
            return 0;
        }
        if (num == 1) {
            return 1;
        }
        if (num == 2) {
            return 2;
        }
        if (num == 3) {
            return 4;
        }

        return stair(num - 1) + stair(num - 2) + stair(num - 3);
    }

    public static int waysToStep(int n) {
        //dp[i]的意义：dp[i]就是小孩上到底i个楼梯有多少种方式
        //dp[i]=?：第i个台阶可以由第i-1个台阶上来，也可以由第i-2个台阶上来,还能从i-3上来..，
        //所以dp[i]=dp[i-1]+dp[i-2]+dp[i-3]
        //初始化条件：dp[0]=1，；dp[1]=2；dp[2]=4
        if(n == 1) {
            return 1;
        }
        if(n == 2) {
            return 2;
        }
        if(n == 3) {
            return 4;
        }
        // int[] dp = new int[n];//没有空间优化
        //进行了空间的优化
        int dp1 = 1;
        int dp2 = 2;
        int dp3 = 4;
        int rel = 0;
        // dp[0] = 1;
        // dp[1] = 2;
        // dp[2] = 4;
        for(int i = 3; i < n; i++) {
            rel = (dp3 + dp2)%1000000007 + dp1;
            rel = rel%1000000007;
            dp1 = dp2;
            dp2 = dp3;
            dp3 = rel;
            // dp[i] = (dp[i-1] + dp[i-2])%1000000007 + dp[i-3];
            // dp[i] = dp[i] % 1000000007;
        }
        return rel;
    }


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
