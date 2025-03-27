package com.swingiot.onboarder;

import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.platform.suite.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
//@SelectPackages("com.swingiot.onboarder")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.swingiot.onboarder")
@SelectClasspathResource("com.swingiot.onboarder")
@CucumberContextConfiguration
@SpringBootTest(classes = CucumberIT.class)
public class CucumberIT {
}
