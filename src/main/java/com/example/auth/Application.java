package com.example.auth;

import com.example.auth.config.GlobalProperties;
import com.example.auth.dto.SampleResponseDTO;
import com.example.auth.service.ExternalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import retrofit2.Response;

import javax.inject.Inject;

@SpringBootApplication
@Slf4j
public class Application {
	@Autowired
	static GlobalProperties globalProperties;


	@Inject
	private ExternalService externalService;


	public static void main(String[] args) {
		log.info("------------>   ",globalProperties);
		SpringApplication.run(Application.class, args);
	}

//	@Bean
	public CommandLineRunner init() {

		return new CommandLineRunner() {
			@Override
			public void run(String... strings) throws Exception {
				log.info("test");
				externalService.getUser(2);
				Response<SampleResponseDTO> response = externalService.getUser(2).execute();
				log.info("okk");
			}
		};
	}

}
