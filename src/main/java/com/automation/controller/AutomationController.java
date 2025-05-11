package com.automation.controller;

import com.automation.service.AutomationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/automation")
public class AutomationController {

    @Autowired
    private AutomationService automationService;

    @PostMapping("/claim/{id}")
    public String processClaim(@PathVariable Long id) {
        automationService.initializeDriver();
        try {
            automationService.processClaimById(id);
            return "Claim processing completed successfully";
        } finally {
            automationService.closeDriver();
        }
    }

    @PostMapping("/enrollment/{id}")
    public String processEnrollment(@PathVariable Long id) {
        automationService.initializeDriver();
        try {
            automationService.processEnrollmentById(id);
            return "Enrollment processing completed successfully";
        } finally {
            automationService.closeDriver();
        }
    }
} 