package com.example.demo;

import com.example.demo.config.GlobalProperties;
import com.example.demo.dto.SampleResponseDTO;
import com.example.demo.service.ExternalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import retrofit2.Response;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@Slf4j
public class DemoApplication {
	@Autowired
	static GlobalProperties globalProperties;


	@Inject
	private ExternalService externalService;


	public static void main(String[] args) {
		log.info("------------>   ",globalProperties);
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
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
