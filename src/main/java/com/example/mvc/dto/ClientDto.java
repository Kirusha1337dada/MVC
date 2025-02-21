package com.example.mvc.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ClientDto
{
    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String passportInfo;
    private String isMarried;
    private String address;
    private String numberPhone;
    private String workPeriod;
    private String jobName;
    private String companyName;
    private BigDecimal amount;
}
