package com.swa.HrmsApplication;

import com.swa.HrmsApplication.controller.EmployeeController;
import com.swa.HrmsApplication.model.Address;
import com.swa.HrmsApplication.model.Employee;
import com.swa.HrmsApplication.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
public class MockMvcWithSpringBootTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;


    @Test
    public void testGetEmployeeByID() throws Exception {

        //Arrange
        Employee employee = Employee.builder()
                .id(1)
                .email("Harry@anymail.com")
                .phone(57555561)
                .address(Address.builder().city("Hobart").country("Aus").street("10 Downing").build())
                .name("Harry")
                .build();

        //Act
        when(employeeService.getEmployeeById(1)).thenReturn(employee);

        //Assert ---> Note: That we are not mocking the controller, but mocking the service this time.
        this.mockMvc.perform(get("/employee/1")).andExpect(status().isOk());
    }

    @Test
    public void testGetEmployee() throws Exception {

        //Arrange
        Employee employee = Employee.builder()
                .id(1)
                .email("Harry@anymail.com")
                .phone(57555561)
                .address(Address.builder().city("Hobart").country("Aus").street("10 Downing").build())
                .name("Harry")
                .build();

        //Act
        when(employeeService.getEmployeeById(1)).thenReturn(employee);

        //Assert ---> Note: That we are not mocking the controller, but mocking the service this time.
        this.mockMvc.perform(get("/employee/1"))
                .andExpect(MockMvcResultMatchers.jsonPath("name").value("Harry"))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void testGetEmployeeList() throws Exception {

        //Arrange
        List<Employee> employeeLists = new ArrayList<>(List.of(

                Employee.builder()
                        .id(1)
                        .email("Harry@anymail.com")
                        .phone(57555561)
                        .address(Address.builder().city("Hobart").country("Aus").street("10 Downing").build())
                        .name("Harry")
                        .build(),
                Employee.builder()
                        .id(2)
                        .email("Ron@anymail.com")
                        .phone(59999461)
                        .address(Address.builder().city("ChristChurch").country("NZ").street("10 Christie").build())
                        .name("Ron")
                        .build(),
                Employee.builder()
                        .id(3)
                        .email("Hermione@anymail.com")
                        .phone(742589612)
                        .address(Address.builder().city("Wellington").country("Scotland").street("10 Imerial").build())
                        .name("Hermione")
                        .build()
        ));

        //Act
        when(employeeService.getAllEmployees()).thenReturn(employeeLists);

        //Assert ---> Note: That we are not mocking the controller, but mocking the service this time.
        this.mockMvc.perform(get("/employee"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Harry"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].address.city").value("Hobart"))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }


}
