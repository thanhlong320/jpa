package com.axonactive.jpa.service.dto;

import com.axonactive.jpa.entity.Employee;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
public class HealthInsuranceDTO {
    private String code;
    private String expirationDate;
    private String address;
    private String registerPlace;
}
