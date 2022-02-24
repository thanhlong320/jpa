package com.axonactive.jpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "project")
@NamedQueries({
        @NamedQuery(name = Project.GET_ALL,query = "from Project p where p.department.id =: departmentId"),
        @NamedQuery(name = Project.GET_PROJECT_BY_ID,query = "from Project p where p.department.id = :departmentId and p.id = :projectId")
})
public class Project {

    private static final String QUALIFIER = "com.axonactive.jpa.entity";
    public static final String GET_ALL = QUALIFIER + "getAllProjectByDepartment";
    public static final String GET_PROJECT_BY_ID = QUALIFIER + "getProjectById";

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(nullable = false, columnDefinition = "varchar(100)")
    private String name;

    @NotNull
    @Column(nullable = false, columnDefinition = "varchar(100)")
    private String area;

    @ManyToOne
    @JoinColumn(name = "managed_department", referencedColumnName = "id")
    private Department department;

}
