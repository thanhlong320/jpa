package com.axonactive.jpa.service.mapper;

import com.axonactive.jpa.controller.request.RelativeRequest;
import com.axonactive.jpa.entity.Relative;
import com.axonactive.jpa.service.dto.RelativeDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface RelativeMapper {
    List<RelativeDTO> relativesToRelativeDTOs(List<Relative> relatives);

    RelativeDTO relativeToRelativeDTO(Relative relativeByEmployeeAndId);

    Relative relativeRequestToRelative(RelativeRequest relativeRequest);
}
