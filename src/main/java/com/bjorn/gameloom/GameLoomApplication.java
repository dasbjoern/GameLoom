package com.bjorn.gameloom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class GameLoomApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameLoomApplication.class, args);
		//   BCryptPasswordEncoder bEncoder = new BCryptPasswordEncoder();
    	//   String encodedPass = bEncoder.encode("bjorn123");
		//   System.out.println("\n" + encodedPass + "\n");
	}

}
