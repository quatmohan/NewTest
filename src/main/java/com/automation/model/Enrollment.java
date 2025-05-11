package com.automation.model;

import lombok.Data;

@Data
public class Enrollment {
    private Long id;
    private String memberId;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String planId;
    private String effectiveDate;
    private String terminationDate;
    private String status;
} 