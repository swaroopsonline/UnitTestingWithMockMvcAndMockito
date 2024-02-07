package com.swa.HrmsApplication.controller;

import com.swa.HrmsApplication.model.Address;
import com.swa.HrmsApplication.model.Employee;
import com.swa.HrmsApplication.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/employee")
	public List<Employee> getEmployeeList() {
		return employeeService.getAllEmployees();
	}

	@GetMapping("/employee/{id}")
	public Employee getEmployee(@PathVariable int id) {
		return employeeService.getEmployeeById(id);
	}

	@PostMapping ("/employee")
	public List<Employee> postEmployee(@RequestBody Employee employee){
		return employeeService.addEmployee(employee);
	}

	@PutMapping("/employee/{id}")
	public Employee putEmployee(@RequestBody Employee employee, @PathVariable int id) {
		return employeeService.updateEmployee(id, employee);
	}

	@DeleteMapping("/employee/{id}")
	public List<Employee> deleteEmployee(@PathVariable int id) {
		return employeeService.deleteEmployee(id);
	}

}
