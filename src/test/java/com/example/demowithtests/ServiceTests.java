package com.example.demowithtests;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.domain.Gender;
import com.example.demowithtests.repository.EmployeeRepository;
import com.example.demowithtests.service.EmployeeServiceBean;
import com.example.demowithtests.util.exception.ResourceNotFoundException;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static javax.management.Query.times;

import static org.apache.logging.log4j.ThreadContext.isEmpty;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Employee Service Tests")
public class ServiceTests {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceBean service;

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = Employee
                .builder()
                .id(1)
                .name("Mark")
                .country("Russian Federation ")
                .email("test@mail.com")
                .gender(Gender.M)
                .isDeleted(Boolean.FALSE)
                .build();
    }

    @AfterEach
    void tearDown() {
        employeeRepository.deleteAll();
    }

    @Test
    @DisplayName("Save employee test")
    public void whenSaveEmployee_shouldReturnEmployee() {

        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
        var created = service.create(employee);
        assertThat(created.getName()).isSameAs(employee.getName());
        verify(employeeRepository).save(employee);
    }

    @Test
    @DisplayName("Get employee by exist id test")
    public void whenGivenId_shouldReturnEmployee_ifFound() {

        Employee employee = new Employee();
        employee.setId(88);
        when(employeeRepository.findById(employee.getId())).thenReturn(Optional.of(employee));
        Employee expected = service.getById(employee.getId());
        assertThat(expected).isSameAs(employee);
        verify(employeeRepository).findById(employee.getId());
    }

    @Test
    @DisplayName("Throw exception when employee not found test")
    public void should_throw_exception_when_employee_doesnt_exist() {

        when(employeeRepository.findById(anyInt())).thenThrow(ResourceNotFoundException.class);
        assertThrows(ResourceNotFoundException.class, () -> employeeRepository.findById(anyInt()));
    }

    @Test
    @DisplayName("Read employee by id test")
    public void readEmployeeByIdTest() {

        when(employeeRepository.findById(employee.getId())).thenReturn(Optional.of(employee));
        Employee expected = service.getById(employee.getId());
        assertThat(expected).isSameAs(employee);
        verify(employeeRepository).findById(employee.getId());
    }

    @Test
    @DisplayName("Read all employees test")
    public void readAllEmployeesTest() {

        when(employeeRepository.findAll()).thenReturn(List.of(employee));
        var list = employeeRepository.findAll();
        assertThat(list.size()).isGreaterThan(0);
        verify(employeeRepository).findAll();
    }

    @Test
    @DisplayName("Delete employee test")
    public void deleteEmployeeTest() {
        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));
        service.removeById(employee.getId());
        verify(employeeRepository).save(employee);
        // емплой не видаляється більше, просто змінюється флажок
        assertThat(employeeRepository.findById(employee.getId()).get().getIsDeleted().equals(Boolean.TRUE));

    }

    @Test
    @DisplayName("Delete all russians")
    public void testDeleteAllRussians() {
        when(employeeRepository.findALLRussians()).thenReturn(List.of(employee));

        var russians = employeeRepository.findALLRussians();

        service.deleteAllRussians(russians);

        verify(employeeRepository).findALLRussians();

        assertThat(russians.get(0).getIsDeleted()).isTrue();

        verify(employeeRepository).saveAllAndFlush(russians);


    }

    @Test
    @DisplayName("Get all russians")
    public void testGetAllRussians() {
        when(employeeRepository.findALLRussians()).thenReturn(List.of(employee));

        var russians = service.getAllRussains();

        assertThat(russians.contains(employee));

        verify(employeeRepository).findALLRussians();


    }

}