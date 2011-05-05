package mw;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	/**
	 * 
	 */
	public static void main(String[] args) {
	  //Initialisierung des Spring Contexts
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

	}

}
