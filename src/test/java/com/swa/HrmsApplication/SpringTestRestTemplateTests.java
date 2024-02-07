package com.swa.HrmsApplication;

import com.swa.HrmsApplication.model.Address;
import com.swa.HrmsApplication.model.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringTestRestTemplateTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	@Test
	void testGetEmployeeByID() {
		//Arrange
		final String baseUrl = "http://localhost:" + port + "/employee/1";
		Employee employee = Employee.builder()
									.id(1)
									.email("Harry@anymail.com")
									.phone(57555561)
									.address(Address.builder().city("Hobart").country("Aus").street("10 Downing").build())
									.name("Harry")
									.build();

		//Act
		Employee responseEntity = this.restTemplate.getForObject(baseUrl, Employee.class);

		//Assert
		assertThat(responseEntity).isEqualTo(employee);
	}

	@Test
	public void testGetEmployees() {
		//Arrange
		final String baseUrl = "http://localhost:" + port + "/employee";
		Employee employee = Employee.builder()
				.id(1)
				.email("Harry@anymail.com")
				.phone(57555561)
				.address(Address.builder().city("Hobart").country("Aus").street("10 Downing").build())
				.name("Harry")
				.build();

		//Act
		Employee[] responseEntity = this.restTemplate.getForObject(baseUrl, Employee[].class);
		Employee responseEmployee = Arrays.stream(responseEntity).filter(x -> x.getId() == 1).findFirst().get();

		//Assert
		assertThat(responseEmployee).isEqualTo(employee);
	}

	@Test
	public void testPOSTEmployees() {
		//Arrange
		final String baseUrl = "http://localhost:" + port + "/employee";
		Employee employee = Employee.builder()
				.id(4)
				.email("Swamy@anymail.com")
				.phone(852963741)
				.address(Address.builder().city("Chennai").country("India").street("12 Madurai").build())
				.name("Swamy")
				.build();

		Employee[] responseEntity = this.restTemplate.postForObject(baseUrl, employee, Employee[].class);
		Employee responseEmployee = Arrays.stream(responseEntity).filter(x -> x.getId() == 4).findFirst().get();

		//assertion
		assertThat(responseEmployee).isEqualTo(employee);
	}

}
