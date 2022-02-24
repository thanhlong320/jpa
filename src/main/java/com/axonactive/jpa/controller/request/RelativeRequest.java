package com.axonactive.jpa.controller.request;

import com.axonactive.jpa.entity.enumerate.Gender;
import com.axonactive.jpa.entity.enumerate.Relationship;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RelativeRequest {
    private String fullName;
    private Gender gender;
    private String phoneNumber;
    private Relationship relationship;
}
