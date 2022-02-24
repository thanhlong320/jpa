package com.axonactive.jpa.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@ToString
@Getter
@Setter
@Entity
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(min = 2, max = 20)
    @Column(nullable = false, columnDefinition = "varchar(200)")
    private String name;

    @NotNull
    @Column(name = "start_date")
    private LocalDate startDate;

}
