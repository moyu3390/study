package com.nijunyang.spring;

import com.nijunyang.spring.config.MainConfig;
import com.nijunyang.spring.listener.MyApplicationEvent;
import com.nijunyang.spring.model.Student;
import com.nijunyang.spring.model.Teacher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//@SpringBootApplication
public class Application {

	public static void main(String[] args) {
//		SpringApplication.run(Application.class, args);
//		ClassPathXmlApplicationContext xmlApplicationContext = new ClassPathXmlApplicationContext();
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
		context.publishEvent(new MyApplicationEvent("想涨工资"));
		Student student = (Student) context.getBean("student");
		Teacher teacher = (Teacher) context.getBean("teacher");
//		Student student2 = (Student) context.getBean(Student.class);
//		System.out.println(student.getClass());
		int a = 0;
	}

}
