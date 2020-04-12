package com.nijunyang.algorithm.math;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Description:
 * Created by nijunyang on 2020/4/11 9:42
 */
public class Student implements Comparable<Student> {

    private int order;

    public Student() {
        this(1);
    }

    public Student(int order) {
        this.order = order;
    }

    @Override
    public int compareTo(Student o) {
        return this.order - o.order;
    }

    @Override
    public String toString() {
        return "order=" + order;

    }

    public static void main(String[] args) {
    	Student s1 = new Student();
    	Student s2 = new Student(4);
    	Student s3 = new Student(2);
    	Student s4 = new Student(-1);
    	Student s5 = new Student(0);

        List<Student> list = new ArrayList<>();
        list.add(s1);
        list.add(s2);
        list.add(s3);
        list.add(s4);
        list.add(s5);
        System.out.println(list);
        Collections.sort(list);
        System.out.println(list);
    }
}
