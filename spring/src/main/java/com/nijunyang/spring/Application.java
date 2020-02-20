package com.nijunyang.spring;

import com.nijunyang.spring.listener.MyApplicationEvent;
import com.nijunyang.spring.model.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//@SpringBootApplication
public class Application {

	public static void main(String[] args) {
//		SpringApplication.run(Application.class, args);
//		ClassPathXmlApplicationContext xmlApplicationContext = new ClassPathXmlApplicationContext();
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
		context.publishEvent(new MyApplicationEvent("想涨工资"));
		Student student = (Student) context.getBean("student");
//		System.out.println(student.getClass());
	}

}
