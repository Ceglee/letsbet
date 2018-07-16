package com.letsbet.webservices;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ResourceLoader;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;

@SpringBootApplication
@EnableScheduling
public class ApplicationConfiguration extends SpringBootServletInitializer {

	@Autowired
	private ResourceLoader resourceLoader;

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ApplicationConfiguration.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(ApplicationConfiguration.class, args);
	}

	@Bean
	public FirebaseAuth firebaseAuth() throws IOException {
		InputStream serviceAccount = resourceLoader.getResource("/WEB-INF/private_key.json").getInputStream();

		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.setDatabaseUrl("https://typer-league.firebaseio.com")
				.build();

		FirebaseApp.initializeApp(options);

		return FirebaseAuth.getInstance();
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
}
