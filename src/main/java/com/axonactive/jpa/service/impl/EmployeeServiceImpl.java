package com.axonactive.jpa.service.impl;

import com.axonactive.jpa.controller.request.EmployeeRequest;
import com.axonactive.jpa.entity.Department;
import com.axonactive.jpa.entity.Employee;
import com.axonactive.jpa.entity.enumerate.Gender;
import com.axonactive.jpa.service.DepartmentService;
import com.axonactive.jpa.service.EmployeeService;
import com.axonactive.jpa.service.dto.DepartmentStatisticDTO;
import com.axonactive.jpa.service.dto.EmployeeDTO;
import com.axonactive.jpa.service.mapper.EmployeeMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RequestScoped
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private static Logger logger = LogManager.getLogger(EmployeeServiceImpl.class);

    private static final long YEAR_OLD = 22;

    @PersistenceContext(unitName = "jpa")
    EntityManager entityManager;

    @Inject
    EmployeeMapper employeeMapper;

    @Inject
    DepartmentService departmentService;

    @Override
    public List<EmployeeDTO> getAllEmployeeByDepartment(int departmentId) {
        logger.info("Get all employee by department id = " + departmentId + " ....");
        TypedQuery<Employee> namedQuery = entityManager.createNamedQuery(Employee.GET_ALL, Employee.class);
        namedQuery.setParameter("departmentId", departmentId);
        List<Employee> employeeList = namedQuery.getResultList();
        return employeeMapper.EmployeesToEmployeeDtos(employeeList);
    }

    @Override
    public EmployeeDTO getEmployeeById(int departmentId, int employeeId) {
        logger.info("Get employee by id " + employeeId + " in department id = " + departmentId + " ....");
        return employeeMapper.EmployeeToEmployeeDto(getEmployeeByIdHelper(departmentId, employeeId));
    }

    public Employee getEmployeeByIdHelper(int departmentId, int employeeId) {
        TypedQuery<Employee> namedQuery = entityManager.createNamedQuery(Employee.GET_EMPLOYEE_BY_ID, Employee.class);
        namedQuery.setParameter("departmentId", departmentId);
        namedQuery.setParameter("employeeId", employeeId);
        return namedQuery.getSingleResult();
    }

    @Override
    public EmployeeDTO addEmployee(int departmentId, EmployeeRequest employeeRequest) {
        Employee employee = employeeMapper.EmployeeRequestToEmployee(employeeRequest);
        employee.setDepartment(departmentService.getDepartmentById(departmentId));
        entityManager.persist(employee);
        return employeeMapper.EmployeeToEmployeeDto(employee);
    }

    @Override
    public void deleteEmployee(int departmentId, int employeeId) {
        logger.info("Delete employee by id " + employeeId + " in department id = " + departmentId + " ....");
        Employee employee = getEmployeeByIdHelper(departmentId, employeeId);
        if (Objects.nonNull(employee)) {
            entityManager.remove(employee);
        }
    }

    @Override
    public EmployeeDTO updateEmployee(int departmentId, int employeeId, EmployeeRequest employeeRequest) {
        logger.info("Update employee by id " + employeeId + " in department id = " + departmentId + " ....");
        try {
            Employee employee = getEmployeeByIdHelper(departmentId, employeeId);
            Employee newEmployee = employeeMapper.EmployeeRequestToEmployee(employeeRequest);
            newEmployee.setId(employee.getId());
            newEmployee.setDepartment(employee.getDepartment());
            entityManager.merge(newEmployee);
            return employeeMapper.EmployeeToEmployeeDto(employee);
        } catch (Exception e){
            logger.warn("Update employee by id " + employeeId + " in department id = " + departmentId + " failed");
            throw new WebApplicationException("Can not update employee by id = " + employeeId);
        }
    }

    @Override
    public List<DepartmentStatisticDTO> getEmployeeGroupByDepartment() {
        List<Employee> employees = getAllEmployee();
        Map<Department, List<Employee>> employeeGroupByDepartment = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment));

        List<DepartmentStatisticDTO> departmentStatisticDTOS = new ArrayList<>();
        employeeGroupByDepartment.forEach(
                (k, v) -> departmentStatisticDTOS.add(
                        DepartmentStatisticDTO.builder()
                                .departmentName(k.getName())
                                .startDate(k.getStartDate())
                                .numOfEmployee(v.size())
                                .numOfFemale(v.stream().filter(employee -> employee.getGender() == Gender.FEMALE).count())
                                .numOfMale(v.stream().filter(employee -> employee.getGender() == Gender.MALE).count())
                                .numOfU30(v.stream().filter(employee -> employee.getDateOfBirth().isAfter(LocalDate.now().minusYears(YEAR_OLD))).count())
                                .build()));

        return departmentStatisticDTOS;
    }

    private List<Employee> getAllEmployee() {
        return entityManager.createQuery("from Employee", Employee.class).getResultList();
    }

    @Override
    public List<EmployeeDTO> getEmployeeByBirthDayMonth(int month) {
        return employeeMapper.EmployeesToEmployeeDtos(getAllEmployee().stream().filter(e -> e.getDateOfBirth().getMonthValue() == month).collect(Collectors.toList()));
    }
}
