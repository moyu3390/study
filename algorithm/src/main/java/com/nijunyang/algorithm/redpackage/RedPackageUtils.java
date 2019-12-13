package com.nijunyang.algorithm.redpackage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Description:
 * Created by nijunyang on 2019/12/12 22:03
 */
public class RedPackageUtils {

    /**
     * 按人数 随机分配红包
     * @param moneyTotal 红包总额
     * @param number 人数
     * @return
     */
    public static List<BigDecimal> shareMoney(BigDecimal moneyTotal, int number) {
        if (moneyTotal.compareTo(new BigDecimal(number).multiply(new BigDecimal("0.01"))) < 0) {
            throw  new RuntimeException("每人至少一分钱.");
        }
        // 按分计算，钱转换成分
        int money = moneyTotal.multiply(BigDecimal.valueOf(100)).intValue();
        //生成一个和人数一样的数组，分布随机数，然后计算随机数占比，根据对应占比分钱。
        double randomCount = 0;
        double[] randomArr = new double[number];
        Random random = new Random();
        for (int i = 0; i < number; i++) {
            int r = random.nextInt(number * 100) + 1;  //避免出现0
            randomArr[i] = r;
            randomCount += r;
        }
        // 根据每个随机数占比计算每份红包金额
        int alreadyShare = 0;
        List<BigDecimal> moneyList = new ArrayList<>(number);
        for (int i = 0; i < number; i++) {
            // 每份占比
            double ratio = randomArr[i] / randomCount;
            /**
             * 向下取整，如果用round，可能导致多个向上舍入之后，最后还没分完，却没钱了，向下取整可以保证正能分完
             * 这样可能导致最后，最后剩余的那份相对而言多一点，最后再将整个集合重新洗牌shuffle
             */
            int shareMoney = (int) Math.floor(ratio * money);
            // 最少1分钱
            if (shareMoney == 0) {
                shareMoney = 1;
            }
            alreadyShare += shareMoney;
            if (i < number - 1) {
                moneyList.add(new BigDecimal(shareMoney).divide(new BigDecimal(100)));
            } else {
                // 最后一份直接把剩余的钱分过去
                moneyList.add(new BigDecimal(money - alreadyShare + shareMoney).divide(new BigDecimal(100)));
            }
        }
        //洗牌
        Collections.shuffle(moneyList);
        return moneyList;
    }
}
