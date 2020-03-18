package com.nijunyang.leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * Created by nijunyang on 2020/3/1 22:28
 */
public class LeetCodeTest {

    @Test
    public void testLeet(){
        Solution solution = new Solution();

        String leetcodeishiring = solution.convert("LEETCODEISHIRING", 3);
        System.out.println(leetcodeishiring);
    }



    /**
     * 从集合找到两字之和等于A的数字及下表
     */
    @Test
    public void test(){
        /**
         * 作差，使用的差值，去匹配后面的数据
         */
        int[] nums = {2,7,13,15};
        int target = 9;

        int[] indexs = new int[2];//记录索引
        Map<Integer, Integer> num2IndexMap = new HashMap();//值和索引的map
        for(int i = 0; i < nums.length; i++){
            if(num2IndexMap.containsKey(nums[i])){
                indexs[0] = num2IndexMap.get(nums[i]);
                indexs[1] = i;
            }
            num2IndexMap.put(target-nums[i], i);
        }
    }

}
