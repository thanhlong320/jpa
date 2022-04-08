//package com.axonactive.jpa.service.impl;
//
//import com.axonactive.jpa.controller.request.EmployeeRequest;
//import com.axonactive.jpa.entity.Department;
//import com.axonactive.jpa.entity.Employee;
//import com.axonactive.jpa.service.DepartmentService;
//import com.axonactive.jpa.service.EmployeeService;
//import com.axonactive.jpa.service.dto.EmployeeDTO;
//import com.axonactive.jpa.service.mapper.EmployeeMapper;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import static org.mockito.Mockito.*;
//import javax.persistence.EntityManager;
//import javax.persistence.TypedQuery;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//@ExtendWith(MockitoExtension.class)
//class EmployeeServiceImplTest {
//
//    @InjectMocks
//    private EmployeeServiceImpl employeeService;
//
//    @Mock
//    private EntityManager entityManager;
//
//    @Mock
//    private TypedQuery<Employee> typedQuery;
//
//    @Mock
//    private EmployeeMapper employeeMapper;
//
//    @Mock
//    private DepartmentService departmentService;
//
//    @Test
//    void getAllEmployeeByDepartment() {
//        Integer departmentId = 1;
//        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
//        List<Employee> employeeList = new ArrayList<>();
//        when(entityManager.createNamedQuery(Employee.GET_ALL, Employee.class)).thenReturn(typedQuery);
//        when(entityManager.createNamedQuery(Employee.GET_ALL, Employee.class).getResultList()).thenReturn(employeeList);
//        when(employeeService.getAllEmployeeByDepartment(departmentId)).thenReturn(employeeDTOList);
//        List<EmployeeDTO> actualEmployeeDTOList = employeeService.getAllEmployeeByDepartment(departmentId);
//        assertEquals(employeeDTOList, actualEmployeeDTOList);
//    }
//
//    @Test
//    void addEmployee() {
//        Integer departmentId = 1;
//        EmployeeRequest employeeRequest = new EmployeeRequest();
//        employeeRequest.setFirstName("Nguyen");
//        employeeRequest.setMiddleName("Thanh");
//        employeeRequest.setLastName("Long");
//        Department department = new Department();
//        EmployeeDTO expectedEmployeeDTO = new EmployeeDTO();
//        expectedEmployeeDTO.setFullName("Nguyen Thanh Long");
//        when(departmentService.getDepartmentById(departmentId)).thenReturn(department);
//        when(employeeService.addEmployee(departmentId, employeeRequest)).thenReturn(expectedEmployeeDTO);
//        EmployeeDTO actualEmployeeDTO = employeeService.addEmployee(departmentId, employeeRequest);
//        assertEquals(expectedEmployeeDTO, actualEmployeeDTO);
//
//    }
//}