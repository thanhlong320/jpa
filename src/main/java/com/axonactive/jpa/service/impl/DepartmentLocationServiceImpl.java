package com.axonactive.jpa.service.impl;

import com.axonactive.jpa.controller.request.DepartmentLocationRequest;
import com.axonactive.jpa.entity.DepartmentLocation;
import com.axonactive.jpa.service.DepartmentLocationService;
import com.axonactive.jpa.service.DepartmentService;
import com.axonactive.jpa.service.dto.DepartmentLocationDTO;
import com.axonactive.jpa.service.mapper.DepartmentLocationMapper;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;


@RequestScoped
@Transactional
public class DepartmentLocationServiceImpl implements DepartmentLocationService {

    @PersistenceContext(name = "jpa")
    EntityManager entityManager;

    @Inject
    DepartmentService departmentService;

    @Inject
    DepartmentLocationMapper departmentLocationMapper;

    public DepartmentLocation getDepartmentLocationByIdHelper(int departmentId, int locationId) throws NoResultException {
        TypedQuery<DepartmentLocation> namedQuery = entityManager.createNamedQuery(DepartmentLocation.GET_LOCATION_BY_ID, DepartmentLocation.class);
        namedQuery.setParameter("departmentId", departmentId);
        namedQuery.setParameter("locationId", locationId);
        return namedQuery.getSingleResult();
    }

    @Override
    public List<DepartmentLocationDTO> getAllLocationByDepartment(int departmentId) {
        TypedQuery<DepartmentLocation> namedQuery = entityManager.createNamedQuery(DepartmentLocation.GET_ALL, DepartmentLocation.class);
        namedQuery.setParameter("departmentId", departmentId);
        List<DepartmentLocation> departmentLocationList = namedQuery.getResultList();
        List<DepartmentLocationDTO> departmentLocationDTOList = departmentLocationMapper.DepartmentLocationsToDepartmentLocationDtos(departmentLocationList);

        return departmentLocationDTOList;
    }

    @Override
    public DepartmentLocationDTO getDepartmentLocationById(int departmentId, int locationId) throws NoResultException {
        return departmentLocationMapper.DepartmentLocationToDepartmentLocationDto(getDepartmentLocationByIdHelper(departmentId, locationId));
    }

    @Override
    public DepartmentLocationDTO addDepartmentLocation(int departmentId, DepartmentLocationRequest departmentLocationRequest) {
        DepartmentLocation departmentLocation = new DepartmentLocation();
        departmentLocation.setLocation(departmentLocationRequest.getLocation());
        departmentLocation.setDepartment(departmentService.getDepartmentById(departmentId));
        entityManager.persist(departmentLocation);
        return departmentLocationMapper.DepartmentLocationToDepartmentLocationDto(departmentLocation);
    }


    @Override
    public void deleteDepartmentLocation(int departmentId, int locationId) throws NoResultException {
        DepartmentLocation departmentLocation = getDepartmentLocationByIdHelper(departmentId, locationId);
        if (Objects.nonNull(departmentLocation)) {
            entityManager.remove(departmentLocation);
        }

    }

    @Override
    public DepartmentLocationDTO updateDepartmentLocation(int departmentId, int locationId, DepartmentLocationRequest departmentLocationRequest) throws NoResultException {
        DepartmentLocation departmentLocation = getDepartmentLocationByIdHelper(departmentId, locationId);
        departmentLocation.setLocation(departmentLocationRequest.getLocation());
        entityManager.merge(departmentLocation);
        return departmentLocationMapper.DepartmentLocationToDepartmentLocationDto(departmentLocation);
    }


}
