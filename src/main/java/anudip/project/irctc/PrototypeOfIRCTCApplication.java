package anudip.project.irctc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import anudip.project.irctc.utility.HiberSessionFactory;

@SpringBootApplication
public class PrototypeOfIRCTCApplication {

	public static void main(String[] args) {
		//SpringApplication.run(PrototypeOfIRCTCApplication.class, args);
		if(HiberSessionFactory.getSessionFactory()!=null)
		{
			System.out.println("object created");
		}
	}

}
