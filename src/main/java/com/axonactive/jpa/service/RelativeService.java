package com.axonactive.jpa.service;


import com.axonactive.jpa.controller.request.RelativeRequest;
import com.axonactive.jpa.service.dto.RelativeDTO;

import java.util.List;

public interface RelativeService {

    List<RelativeDTO> getRelatives(int employeeId);

    RelativeDTO getRelativeById(int employeeId, int relativeId);

    RelativeDTO addRelative(int employeeId, RelativeRequest relativeRequest);

    RelativeDTO updateRelative(int employeeId, int relativeId, RelativeRequest relativeRequest);

    void deleteRelative(int employeeId, int relativeId);
}
