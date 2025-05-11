package com.automation.stepdefs;

import com.automation.service.AutomationService;
import io.cucumber.java.en.Given;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest
public class SauceDemoLoginSteps {
    @Autowired
    private AutomationService automationService;

    @Given("I perform SauceDemo login")
    public void i_perform_saucedemo_login() {
        automationService.initializeDriver();
        automationService.loginToSauceDemo();
        automationService.closeDriver();
    }

    // ... other steps ...
} 