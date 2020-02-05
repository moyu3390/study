package com.nijunyang.util.enums;
import java.util.HashSet;
import java.util.Set;

/**
 * Description: 
 * Created by nijunyang on 2020/1/8 11:36
 */
public final class ExchangeTypeUtils {

    private ExchangeTypeUtils() {
    }

    public static Set<ExchangeType> parseEnum(int values){
        Set<ExchangeType> set = new HashSet<>();
        split(values, set);
        Integer count = set.stream().map(exchangeType -> exchangeType.getValue()).reduce(Integer::sum).get();
        if (count != values) {
            set.clear();
            throw new IllegalArgumentException("check your values, it is not a lot of square sums of two");
        }
        return set;
    }

    private static void split(int values, Set<ExchangeType> list) {
        if (values == 1) {
            list.add(ExchangeType.getByValue(1));
            return;
        }
        double sqrt = Math.sqrt(values);
        int power = (int) Math.floor(sqrt);
        int value1 = (int) Math.pow(2, power);
        list.add(ExchangeType.getByValue(value1));
        if (value1 == values) {
            //检查是否和原值等，若相等直接返回
            return;
        }
        else {
            int value2 = values - value1;
            split(value2,  list);
        }
    }

    public static void main(String[] args){
        Set<ExchangeType> set = ExchangeTypeUtils.parseEnum(8);
        System.out.println(set);
    }
}
