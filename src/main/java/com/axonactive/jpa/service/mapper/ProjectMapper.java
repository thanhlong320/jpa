package com.axonactive.jpa.service.mapper;

import com.axonactive.jpa.entity.Project;
import com.axonactive.jpa.service.dto.ProjectDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProjectMapper {
    ProjectDTO ProjectToProjectDto(Project Project);
    List<ProjectDTO> ProjectsToProjectDtos (List<Project> Projects);
}
