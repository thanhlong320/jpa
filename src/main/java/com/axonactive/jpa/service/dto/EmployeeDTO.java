package com.axonactive.jpa.service.dto;

import com.axonactive.jpa.entity.enumerate.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Getter;
import lombok.Setter;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Setter
@Getter
public class EmployeeDTO {
    @NotNull
    private String fullName;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;
    private Gender gender;
    private double salary;
    private String departmentName;
}
