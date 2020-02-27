package com.nijunyang.spring;

import com.nijunyang.spring.config.MainConfig;
import com.nijunyang.spring.controller.MyController;
import com.nijunyang.spring.listener.MyApplicationEvent;
import com.nijunyang.spring.model.Student;
import com.nijunyang.spring.model.Teacher;
import org.springframework.aop.framework.AopContext;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

//@SpringBootApplication
public class Application {

	public static void main(String[] args) {
//		SpringApplication.run(Application.class, args);
//		ClassPathXmlApplicationContext xmlApplicationContext = new ClassPathXmlApplicationContext();
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
//		context.publishEvent(new MyApplicationEvent("想涨工资"));
//		Student student = (Student) context.getBean("student");
//		Teacher teacher = (Teacher) context.getBean("teacher");

		MyController myController = context.getBean(MyController.class);
		myController.methodA();
		System.out.println("----");
		myController.methodB();

//		Student student2 = (Student) context.getBean(Student.class);
//		System.out.println(student.getClass());
		int a = 0;
	}

}
