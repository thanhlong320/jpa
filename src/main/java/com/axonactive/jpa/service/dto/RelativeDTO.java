package com.axonactive.jpa.service.dto;

import com.axonactive.jpa.entity.enumerate.Gender;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RelativeDTO {
    private String fullName;
    private Gender gender;
    private String phoneNumber;
    private String relationship;
}
