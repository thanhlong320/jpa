package com.axonactive.jpa.service;

import com.axonactive.jpa.controller.request.HealthInsuranceRequest;
import com.axonactive.jpa.service.dto.HealthInsuranceDTO;

public interface HealthInsuranceService {
    HealthInsuranceDTO getHealthInsuranceByEmployeeId(Integer id);

    HealthInsuranceDTO addHealthInsuranceByEmployeeId(Integer employeeId, HealthInsuranceRequest healthInsuranceRequest);

    HealthInsuranceDTO updateHealthInsuranceByEmployeeId(Integer employeeId, HealthInsuranceRequest healthInsuranceRequest);

    void deleteHealthInsuranceByEmployeeId(Integer employeeId);
}
