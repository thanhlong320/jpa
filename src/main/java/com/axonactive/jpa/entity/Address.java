package com.axonactive.jpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "no")
    private String no;

    @Column(name = "village")
    private String village;

    @Column(name = "district")
    private String district;

    @Column(name = "province")
    private String province;
}
