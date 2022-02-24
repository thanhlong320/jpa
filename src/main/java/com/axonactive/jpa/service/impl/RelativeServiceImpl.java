package com.axonactive.jpa.service.impl;

import com.axonactive.jpa.controller.request.RelativeRequest;
import com.axonactive.jpa.entity.Employee;
import com.axonactive.jpa.entity.Relative;
import com.axonactive.jpa.service.RelativeService;
import com.axonactive.jpa.service.dto.RelativeDTO;
import com.axonactive.jpa.service.mapper.RelativeMapper;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@RequestScoped
@Transactional
public class RelativeServiceImpl implements RelativeService {

    @PersistenceContext
    EntityManager entityManager;

    @Inject
    RelativeMapper relativeMapper;

    @Override
    public List<RelativeDTO> getRelatives(int employeeId) {
        List<Relative> relatives = entityManager.createNamedQuery(Relative.GET_ALL_BY_EMPLOYEE, Relative.class)
                .setParameter("employeeId", employeeId)
                .getResultList();
        return relativeMapper.relativesToRelativeDTOs(relatives);
    }

    @Override
    public RelativeDTO getRelativeById(int employeeId, int relativeId) {
        Relative relativeByEmployeeAndId = entityManager.createNamedQuery(Relative.GET_BY_EMPLOYEE_AND_ID, Relative.class)
                .setParameter("employeeId", employeeId)
                .setParameter("relativeId", relativeId).getSingleResult();
        return relativeMapper.relativeToRelativeDTO(relativeByEmployeeAndId);
    }

    @Override
    public RelativeDTO addRelative(int employeeId, RelativeRequest relativeRequest) {
        Employee checkingEmployee = entityManager.find(Employee.class, employeeId);
        Relative newRelative = new Relative();
        if (Objects.nonNull(checkingEmployee)) {
            newRelative = relativeMapper.relativeRequestToRelative(relativeRequest);
            newRelative.setEmployee(checkingEmployee);
            entityManager.persist(newRelative);
        }
        return relativeMapper.relativeToRelativeDTO(newRelative);
    }

    @Override
    public RelativeDTO updateRelative(int employeeId, int relativeId, RelativeRequest relativeRequest) {
        Employee checkingEmployee = entityManager.find(Employee.class, employeeId);
        Relative updatingRelative = entityManager.find(Relative.class, relativeId);
        if (Objects.nonNull(checkingEmployee) && Objects.nonNull(updatingRelative)) {
            updatingRelative.setFullName(relativeRequest.getFullName());
            updatingRelative.setGender(relativeRequest.getGender());
            updatingRelative.setPhoneNumber(relativeRequest.getPhoneNumber());
            updatingRelative.setRelationship(relativeRequest.getRelationship());
            entityManager.merge(updatingRelative);
        }
        return relativeMapper.relativeToRelativeDTO(updatingRelative);
    }

    @Override
    public void deleteRelative(int employeeId, int relativeId) {
        Employee checkingEmployee = entityManager.find(Employee.class, employeeId);
        Relative updatingRelative = entityManager.find(Relative.class, relativeId);
        if (Objects.nonNull(checkingEmployee) && Objects.nonNull(updatingRelative)) {
            entityManager.remove(updatingRelative);
        }
    }
}
