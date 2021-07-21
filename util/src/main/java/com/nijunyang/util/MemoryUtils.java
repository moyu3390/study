package com.nijunyang.util;

import org.apache.lucene.util.RamUsageEstimator;

/**
 * @author nijunyang
 * @since 2021/7/21
 */
public class MemoryUtils {

    public static  Object calc(Object o) {
        return RamUsageEstimator.humanSizeOf(o);
    }

    public static void main(String[] args) {
        System.out.println(MemoryUtils.calc(new Object()));
    }

}
