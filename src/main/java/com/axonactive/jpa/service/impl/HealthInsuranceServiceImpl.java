package com.axonactive.jpa.service.impl;

import com.axonactive.jpa.controller.request.HealthInsuranceRequest;
import com.axonactive.jpa.entity.Employee;
import com.axonactive.jpa.entity.HealthInsurance;
import com.axonactive.jpa.service.HealthInsuranceService;
import com.axonactive.jpa.service.dto.HealthInsuranceDTO;
import com.axonactive.jpa.service.mapper.HealthInsuranceMapper;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@Transactional
public class HealthInsuranceServiceImpl implements HealthInsuranceService {

    @PersistenceContext(name = "jpa")
    private EntityManager entityManager;

    @Inject
    private HealthInsuranceMapper healthInsuranceMapper;

    @Override
    public HealthInsuranceDTO getHealthInsuranceByEmployeeId(Integer employeeId) {
        TypedQuery<HealthInsurance> query = entityManager.createQuery("SELECT h FROM HealthInsurance h WHERE h.employee.id = :employeeId", HealthInsurance.class);
        query.setParameter("employeeId", employeeId);
        return healthInsuranceMapper.maptoDTO(query.getSingleResult());
    }

    @Override
    public HealthInsuranceDTO addHealthInsuranceByEmployeeId(Integer employeeId, HealthInsuranceRequest healthInsuranceRequest) {
        Employee employee = entityManager.find(Employee.class, employeeId);
        HealthInsurance healthInsurance = new HealthInsurance();
        healthInsurance.setCode(healthInsuranceRequest.getCode());
        healthInsurance.setExpirationDate(healthInsuranceRequest.getExpirationDate());
        healthInsurance.setRegisterPlace(healthInsuranceRequest.getRegisterPlace());
        healthInsurance.setAddress(healthInsuranceRequest.getAddress());
        healthInsurance.setEmployee(employee);
        entityManager.persist(healthInsurance);
        return healthInsuranceMapper.maptoDTO(healthInsurance);
    }

    @Override
    public HealthInsuranceDTO updateHealthInsuranceByEmployeeId(Integer employeeId, HealthInsuranceRequest healthInsuranceRequest) {
        TypedQuery<HealthInsurance> query = entityManager.createQuery("SELECT h FROM HealthInsurance h WHERE h.employee.id = :employeeId", HealthInsurance.class);
        query.setParameter("employeeId", employeeId);
        HealthInsurance healthInsurance = query.getSingleResult();
        healthInsurance.setCode(healthInsuranceRequest.getCode());
        healthInsurance.setExpirationDate(healthInsuranceRequest.getExpirationDate());
        healthInsurance.setRegisterPlace(healthInsuranceRequest.getRegisterPlace());
        healthInsurance.setAddress(healthInsuranceRequest.getAddress());
        entityManager.merge(healthInsurance);
        return healthInsuranceMapper.maptoDTO(healthInsurance);
    }

    @Override
    public void deleteHealthInsuranceByEmployeeId(Integer employeeId) {
        Query query = entityManager.createQuery("DELETE FROM HealthInsurance h WHERE h.employee.id = :employeeId");
        query.setParameter("employeeId", employeeId);
        query.executeUpdate();
    }
}
