package com.Games.LojaGames;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LojaGamesApplication {

	public static void main(String[] args) {
		SpringApplication.run(LojaGamesApplication.class, args);
	}

}