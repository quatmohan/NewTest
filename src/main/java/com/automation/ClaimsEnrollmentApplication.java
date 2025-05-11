package com.automation;

import com.automation.service.AutomationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ClaimsEnrollmentApplication {

    @Autowired
    private AutomationService automationService;

    public static void main(String[] args) {
        SpringApplication.run(ClaimsEnrollmentApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            if (args.length < 2) {
                System.out.println("Usage: java -jar application.jar [claim|enrollment] [id]");
                System.out.println("Example: java -jar application.jar claim 123");
                System.out.println("Example: java -jar application.jar enrollment 456");
                return;
            }

            String type = args[0].toLowerCase();
            Long id;
            try {
                id = Long.parseLong(args[1]);
            } catch (NumberFormatException e) {
                System.out.println("Error: ID must be a number");
                return;
            }

            automationService.initializeDriver();
            try {
                switch (type) {
                    case "claim":
                        System.out.println("Processing claim with ID: " + id);
                        automationService.processClaimById(id);
                        break;
                    case "enrollment":
                        System.out.println("Processing enrollment with ID: " + id);
                        automationService.processEnrollmentById(id);
                        break;
                    default:
                        System.out.println("Error: Invalid type. Use 'claim' or 'enrollment'");
                        break;
                }
            } finally {
                automationService.closeDriver();
            }
        };
    }
} 