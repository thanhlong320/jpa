package com.axonactive.jpa.entity;

import com.axonactive.jpa.entity.enumerate.Nationality;
import com.axonactive.jpa.entity.enumerate.converter.NationalityAttributeConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Embedded
    private Information information;

    @Convert(converter = NationalityAttributeConverter.class)
    private Nationality nationality;

    @Transient
    @Column(name = "hidden")
    private String hidden;
}
