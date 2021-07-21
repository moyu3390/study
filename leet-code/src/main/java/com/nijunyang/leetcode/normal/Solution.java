package com.nijunyang.leetcode.normal;

/**
 * @author nijunyang
 * @since 2021/6/23
 */
public class Solution {
    public int removeDuplicates(int[] nums) {
        int pre = 0;
        int max = nums[0];
        for (int num : nums) {
            //前面的和比当前数小，丢弃前面的
            pre = Math.max(pre + num, num);
            max = Math.max(pre, max);
        }
        return max;
    }


    public static void main(String[] args) {
//        int[] nums = {1,1,2};
        int[] nums = {3,2,2,3};
        Solution solution = new Solution();
        System.out.println(solution.removeDuplicates(nums));;
    }
}
