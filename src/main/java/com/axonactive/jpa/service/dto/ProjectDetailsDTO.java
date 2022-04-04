package com.axonactive.jpa.service.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class ProjectDetailsDTO {
    @NotNull
    private String name;
    @NotNull
    private String area;

    private Integer totalEmployee;

    private Double totalHour;

    private Double totalSalary;
}
