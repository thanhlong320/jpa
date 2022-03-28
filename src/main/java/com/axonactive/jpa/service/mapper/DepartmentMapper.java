package com.axonactive.jpa.service.mapper;

import com.axonactive.jpa.entity.Department;
import com.axonactive.jpa.entity.Project;
import com.axonactive.jpa.service.dto.DepartmentDTO;
import com.axonactive.jpa.service.dto.ProjectDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface DepartmentMapper {
    DepartmentDTO toDTO(Department department);
    List<DepartmentDTO> toDTOs (List<Department> departments);
}
