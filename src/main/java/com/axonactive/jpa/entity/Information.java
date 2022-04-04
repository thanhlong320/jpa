package com.axonactive.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Information {
    @Column
    private String phone;

    @Column
    private String email;

    @Column
    private String address;
}
