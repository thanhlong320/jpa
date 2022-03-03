package com.axonactive.jpa.service.mapper;

import com.axonactive.jpa.entity.HealthInsurance;
import com.axonactive.jpa.service.dto.HealthInsuranceDTO;
import org.mapstruct.Mapper;

@Mapper
public interface HealthInsuranceMapper {
    HealthInsuranceDTO maptoDTO(HealthInsurance healthInsurance);
}
