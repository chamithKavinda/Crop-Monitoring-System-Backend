package org.example.cropmonitoringsystem;

import org.modelmapper.ModelMapper;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.PrintStream;

@SpringBootApplication
public class CropMonitoringSystemApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(CropMonitoringSystemApplication.class);
		app.setBanner(new CustomBanner()); // Set the custom banner
		app.run(args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// Custom banner implementation
	static class CustomBanner implements Banner {
		@Override
		public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
			out.println("==========================================");
			out.println("      Welcome to the Green Shadow Crop Monitoring       ");
			out.println("               System Application          ");
			out.println("==========================================");
		}
	}
}
