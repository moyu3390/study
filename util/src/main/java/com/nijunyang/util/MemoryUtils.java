package com.nijunyang.util;

import org.apache.lucene.util.RamUsageEstimator;

/**
 * @author nijunyang
 * @since 2021/7/21
 */
public class MemoryUtils {

    public static long calc(Object o) {
        return RamUsageEstimator.sizeOfObject(o);
    }

    public static long calcSizeOfInstance(Class clazz) {
        return RamUsageEstimator.shallowSizeOfInstance(clazz);
    }

    public static void main(String[] args) {
        System.out.println(MemoryUtils.calc(new Object()));
        System.out.println(MemoryUtils.calcSizeOfInstance(Object.class));
    }

}
