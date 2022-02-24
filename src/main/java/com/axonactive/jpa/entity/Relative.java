package com.axonactive.jpa.entity;

import com.axonactive.jpa.entity.enumerate.Gender;
import com.axonactive.jpa.entity.enumerate.Relationship;
import jdk.jfr.Name;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "relatives")
@Getter
@Setter
@NamedQueries({
        @NamedQuery(name = Relative.GET_ALL_BY_EMPLOYEE, query = "from Relative r where r.employee.id = :employeeId"),
        @NamedQuery(name = Relative.GET_BY_EMPLOYEE_AND_ID, query = "from Relative r where r.employee.id = :employeeId and r.id =: relativeId")
})
public class Relative {
    public final static String QUALIFIER = "com.axonactive.jpa.entity";
    public final static String GET_ALL_BY_EMPLOYEE = QUALIFIER + "getAllByEmployee";
    public final static String GET_BY_EMPLOYEE_AND_ID = QUALIFIER + "getByEmployeeAndId";

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @NotNull
    @Column(name = "full_name", nullable = false, columnDefinition = "varchar(100)")
    private String fullName;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotNull
    @Column(name = "phone_number", nullable = false, columnDefinition = "varchar(50)")
    private String phoneNumber;

    @NotNull
    @Column(nullable = false, columnDefinition = "varchar(15)")
    @Enumerated(EnumType.STRING)
    private Relationship relationship;
}
