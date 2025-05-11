package com.automation;

import com.automation.repository.ClaimRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
public class DatabaseConnectionTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ClaimRepository claimRepository;

    @Test
    public void testDatabaseConnection() {
        try {
            // Test basic connection
            String result = jdbcTemplate.queryForObject("SELECT 'Connection Successful'", String.class);
            System.out.println("Database Connection Test: " + result);

            // Test claims table
            System.out.println("\nTesting Claims Table:");
            System.out.println("----------------------");
            claimRepository.findAll().forEach(claim -> {
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
                System.out.println("----------------------");
            });

        } catch (Exception e) {
            System.err.println("Database Connection Error: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
} 