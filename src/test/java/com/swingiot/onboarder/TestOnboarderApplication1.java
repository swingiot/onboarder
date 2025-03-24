package com.swingiot.onboarder;

import org.springframework.boot.SpringApplication;

public class TestOnboarderApplication1 {

	public static void main(String[] args) {
		SpringApplication.from(OnboarderApplication::main).with(TestcontainersConfiguration1.class).run(args);
	}

}
