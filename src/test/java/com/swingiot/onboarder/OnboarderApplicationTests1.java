package com.swingiot.onboarder;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration1.class)
@SpringBootTest
class OnboarderApplicationTests1 {

	@Test
	void contextLoads() {
	}

}
