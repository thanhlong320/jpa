package com.axonactive.jpa.entity;

import com.axonactive.jpa.entity.enumerate.Location;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "department_location")
@NamedQueries({
        @NamedQuery(name = DepartmentLocation.GET_ALL, query = "from DepartmentLocation dl where dl.department.id = :departmentId"),
        @NamedQuery(name = DepartmentLocation.GET_LOCATION_BY_ID,
                query = "from DepartmentLocation dl where dl.department.id = :departmentId and dl.id = :locationId"),

})
public class DepartmentLocation {
    private static final String QUALIFIER = "com.axonactive.jpa.entity";
    public static final String GET_ALL = QUALIFIER + "getAllLocationByDepartment";
    public static final String GET_LOCATION_BY_ID = QUALIFIER + "getLocationById";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @NotNull
    @Column(nullable = false, columnDefinition = "varchar(100)")
    @Enumerated(EnumType.STRING)
    private Location location;

}
