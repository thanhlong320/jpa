package com.axonactive.jpa.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HealthInsuranceRequest {
    private String code;
    private String expirationDate;
    private String address;
    private String registerPlace;
}
