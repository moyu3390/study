package com.nijunyang.spring;

import com.nijunyang.spring.model.Student;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//@SpringBootApplication
public class Application {

	public static void main(String[] args) {

//		ClassPathXmlApplicationContext xmlApplicationContext = new ClassPathXmlApplicationContext();

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
		Student student = (Student) context.getBean("student");
		System.out.println(student.getClass());
	}

}
