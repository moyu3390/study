package com.nijunyang.algorithm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

//@SpringBootApplication
public class AlgorithmApplication {

	public static void main(String[] args) {
//		SpringApplication.run(AlgorithmApplication.class, args);

		Stack stack = new Stack();
		stack.push("1");
		System.out.println(peibolaqi(8));
	}

	/**
	 * 裴波那契数列
	 * @param n
	 * @return
	 */
	public static int peibolaqi(int n) {
		if (n <= 2) {
			return n == 0 ? 0 : 1;
		}

		return peibolaqi(n - 1) + peibolaqi(n - 2);
	}
}
