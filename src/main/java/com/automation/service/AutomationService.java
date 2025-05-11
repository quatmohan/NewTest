package com.automation.service;

import com.automation.model.Claim;
import com.automation.model.Enrollment;
import com.automation.repository.ClaimRepository;
import com.automation.repository.EnrollmentRepository;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutomationService {

    @Autowired
    private ClaimRepository claimRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    private WebDriver driver;

    public void initializeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }

    public void processClaimById(Long claimId) {
        Claim claim = claimRepository.findById(claimId);
        if (claim != null) {
            System.out.println("Processing Claim:");
            System.out.println("Claim ID: " + claim.getClaimId());
            System.out.println("Claim Number: " + claim.getClaimNumber());
            System.out.println("Member ID: " + claim.getMemberId());
            System.out.println("Claim Date: " + claim.getClaimDate());
            System.out.println("Claim Amount: " + claim.getClaimAmount());
            System.out.println("Claim Status: " + claim.getClaimStatus());
            System.out.println("Provider ID: " + claim.getProviderId());
            System.out.println("Service Type: " + claim.getServiceType());
            System.out.println("Created Date: " + claim.getCreatedDate());
            System.out.println("Updated Date: " + claim.getUpdatedDate());
            // Add your Selenium automation logic here for claims processing
        }
    }

    public void processEnrollmentById(Long enrollmentId) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId);
        if (enrollment != null) {
            System.out.println("Processing Enrollment:");
            System.out.println("Member ID: " + enrollment.getMemberId());
            System.out.println("Name: " + enrollment.getFirstName() + " " + enrollment.getLastName());
            System.out.println("Date of Birth: " + enrollment.getDateOfBirth());
            System.out.println("Plan ID: " + enrollment.getPlanId());
            System.out.println("Effective Date: " + enrollment.getEffectiveDate());
            System.out.println("Status: " + enrollment.getStatus());
            // Add your Selenium automation logic here for enrollment processing
        }
    }

    public void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    public void printAllClaims() {
        List<Claim> claims = claimRepository.findAll();
        System.out.println("\n=== All Claims in Database ===");
        System.out.println("Total claims found: " + claims.size());
        System.out.println("\nClaim Details:");
        System.out.println("----------------------------------------");
        for (Claim claim : claims) {
            System.out.println("Claim ID: " + claim.getClaimId());
            System.out.println("Claim Number: " + claim.getClaimNumber());
            System.out.println("Member ID: " + claim.getMemberId());
            System.out.println("Claim Date: " + claim.getClaimDate());
            System.out.println("Claim Amount: $" + claim.getClaimAmount());
            System.out.println("Claim Status: " + claim.getClaimStatus());
            System.out.println("Provider ID: " + claim.getProviderId());
            System.out.println("Service Type: " + claim.getServiceType());
            System.out.println("Created Date: " + claim.getCreatedDate());
            System.out.println("Updated Date: " + claim.getUpdatedDate());
            System.out.println("----------------------------------------");
        }
    }
} 