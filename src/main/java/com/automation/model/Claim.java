package com.automation.model;

import lombok.Data;

@Data
public class Claim {
    private Long id;
    private String claimNumber;
    private String memberId;
    private String serviceDate;
    private Double amount;
    private String status;
    private String providerId;
} 