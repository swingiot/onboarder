package com.swingiot.onboarder;

import org.springframework.boot.SpringApplication;

public class OnboarderApplicationTestContainerIT {

	public static void main(String[] args) {
		SpringApplication.from(OnboarderApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
