package com.axonactive.jpa.entity;

import com.axonactive.jpa.entity.enumerate.Gender;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@ToString
@Table(name = "employee")
@NamedQueries({
        @NamedQuery(name = Employee.GET_ALL, query = "from Employee e where e.department.id = :departmentId"),
        @NamedQuery(name = Employee.GET_EMPLOYEE_BY_ID, query = "from Employee e where e.department.id = :departmentId and e.id = :employeeId")
})
public class Employee {
    private static final String QUALIFIER = "com.axonactive.jpa.entity";
    public static final String GET_ALL = QUALIFIER + "getAllByDepartment";
    public static final String GET_EMPLOYEE_BY_ID = QUALIFIER + "getEmployeeById";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull
    @Size(min = 2, max = 20)
    @Column(name = "first_name", nullable = false, columnDefinition = "varchar(20)")
    private String firstName;

    @NotNull
    @Size(min = 2, max = 20)
    @Column(name = "middle_name", nullable = false, columnDefinition = "varchar(20)")
    private String middleName;

    @NotNull
    @Size(min = 2, max = 20)
    @Column(name = "last_name", nullable = false, columnDefinition = "varchar(20)")
    private String lastName;

    @NotNull
    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotNull
    @Column(nullable = false)
    private double salary;

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id", nullable = false)
    private Department department;

}
