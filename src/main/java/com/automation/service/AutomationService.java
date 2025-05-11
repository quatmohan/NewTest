package com.automation.service;

import com.automation.model.Claim;
import com.automation.model.Enrollment;
import com.automation.repository.ClaimRepository;
import com.automation.repository.EnrollmentRepository;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.util.List;

@Service
public class AutomationService {

    @Autowired
    private ClaimRepository claimRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    private WebDriver driver;
    private ExtentReports extent;
    private ExtentTest test;

    public void initializeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }

    private void setupExtentReports() {
        if (extent == null) {
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("extent-report.html");
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
        }
    }

    public void loginToSauceDemo() {
        setupExtentReports();
        test = extent.createTest("SauceDemo Login Test");
        try {
            driver.get("https://www.saucedemo.com/");
            test.log(Status.INFO, "Navigated to SauceDemo");
            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();
            // Check for successful login by looking for an element on the products page
            boolean loggedIn = driver.getCurrentUrl().contains("inventory.html");
            if (loggedIn) {
                test.log(Status.PASS, "Login successful");
            } else {
                test.log(Status.FAIL, "Login failed");
            }
        } catch (Exception e) {
            test.log(Status.FAIL, "Exception during login: " + e.getMessage());
        } finally {
            extent.flush();
        }
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
            loginToSauceDemo();
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