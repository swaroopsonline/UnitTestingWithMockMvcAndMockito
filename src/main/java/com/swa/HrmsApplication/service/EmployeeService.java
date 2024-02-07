package com.swa.HrmsApplication.service;

import com.swa.HrmsApplication.model.Address;
import com.swa.HrmsApplication.model.Employee;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;


@Service
public class EmployeeService {

    //Entity
    private List<Employee> employeeLists = new ArrayList<>(List.of(

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

    public List<Employee> getAllEmployees(){
        return employeeLists;
    }

    public Employee getEmployeeById(int id) {
        return employeeLists.stream()
                .filter(x -> x.getId() == id)
                .findFirst().get();
    }

    public List<Employee> addEmployee(Employee employee) {
        employeeLists.add(employee);
        return employeeLists;
    }

    public Employee updateEmployee(int id, Employee employee) {
        return employeeLists.stream()
                .filter(x -> x.getId() == id)
                .peek(o -> o.setName(employee.getName()))
                .peek(o -> o.setPhone(employee.getPhone()))
                .peek(o -> o.setEmail(employee.getEmail()))
                .peek(o -> o.setAddress(employee.getAddress()))
                .findFirst().get();
    }

    public List<Employee> deleteEmployee(int id) {
        employeeLists.removeIf(x -> x.getId() == id);
        return employeeLists;
    }
}
