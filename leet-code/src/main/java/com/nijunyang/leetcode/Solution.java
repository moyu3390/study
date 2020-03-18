package com.nijunyang.leetcode;

import java.util.Arrays;

/**
 * Description:
 * Created by nijunyang on 2020/3/1 22:37
 */
public class Solution {

    public String convert(String s, int numRows) {
        if (s==null || s.isEmpty() || numRows < 1) {
            return s;
        }
        //构造对应函数的StringBuilder存放字符
        StringBuilder[] stringBuilders = new StringBuilder[numRows];
        for (int i = 0; i < stringBuilders.length; i++) {
          stringBuilders[i] = new StringBuilder();
        }

        char[] chars = s.toCharArray();
        int nextRow = 1; //下一行的标记 默认是1
        int index = 0;
        for (char letter : chars) {
            stringBuilders[index].append(letter);
            index += nextRow;
            //最后一行将下一行的处理标记置为-1
            if (index == numRows -1) {
                nextRow = -1;
            }
            //首行将下一行的处理标记置为1
            if (index == 0) {
                nextRow = 1;
            }
        }
        StringBuilder result = new StringBuilder();
        for (StringBuilder sb : stringBuilders) {
            result.append(sb);
        }
        return result.toString();
    }


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return null;
    }


    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

}
