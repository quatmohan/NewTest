package com.automation.model;

import lombok.Data;

@Data
public class Claim {
    private Long claimId;
    private String memberId;
    private String claimNumber;
    private String claimDate;
    private String claimStatus;
    private Double claimAmount;
    private String providerId;
    private String serviceType;
    private String createdDate;
    private String updatedDate;
} 