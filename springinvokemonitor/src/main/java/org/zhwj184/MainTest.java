package org.zhwj184;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainTest {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:service.xml");
		Thread.sleep(100000000);
	}

}
