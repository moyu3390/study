package com.nijunyang.algorithm.redpackage;

import java.math.BigDecimal;
import java.util.List;

/**
 * Description:
 * Created by nijunyang on 2019/12/12 22:24
 */
public class Test {

    public static void main(String[] args) {
//        RedPackage redPackage = new RedPackage();
//        redPackage.setRemainMoney(new BigDecimal(10));
//        redPackage.setRemainCount(10);
//
//        for (int i = 0; i < 10; i++) {
//            new Thread(()->{
//                BigDecimal randomMoney = RedUtils.getRandomMoney(redPackage);
//                System.out.println(randomMoney);
//            }).start();
//        }
        List<BigDecimal> redPackageList = RedUtils.math(BigDecimal.valueOf(100_000_00), 100_000_0);
        System.out.println(redPackageList);
        System.out.println(redPackageList.stream().reduce(new BigDecimal(0), BigDecimal::add));
    }
}
