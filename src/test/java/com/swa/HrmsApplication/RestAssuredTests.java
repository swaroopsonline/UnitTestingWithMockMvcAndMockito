package com.swa.HrmsApplication;

import com.swa.HrmsApplication.model.Address;
import com.swa.HrmsApplication.model.Employee;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestAssuredTests {

    @Value("${base.url}")
    private String baseUrl;

    @LocalServerPort
    private int port;

    @Test
    public void testGETEmployeeByID() {
        given()
                .baseUri(baseUrl)
                .port(port)
                .basePath("/employee/1")
                .get().then().assertThat().body("name", Matchers.equalTo("Harry"));
    }

    @Test
    public void testGetEmployees() {

        //Arrange
        Employee employee = Employee.builder()
                .id(1)
                .email("Harry@anymail.com")
                .phone(57555561)
                .address(Address.builder().city("Hobart").country("Aus").street("10 Downing").build())
                .name("Harry")
                .build();

        //Act
        Response response = given().baseUri(baseUrl)
                .port(port)
                .basePath("/employee/1")
                .get();

        Employee responseEntity = response.body().as(Employee.class);

        //Assert
        assertThat(responseEntity).isEqualTo(employee);
    }

    @Test
    public void testPOSTEmployee() {
        //Arrange
        Employee employee = Employee.builder()
                .id(4)
                .email("Swamy@anymail.com")
                .phone(852963741)
                .address(Address.builder().city("Chennai").country("India").street("12 Madurai").build())
                .name("Swamy")
                .build();

        //Act
        Response response = given().baseUri(baseUrl)
                .port(port)
                .basePath("/employee")
                .contentType("application/json")
                .body(employee)
                .post();

        Employee[] responseEntity = response.body().as(Employee[].class);
        Employee responseEmployee = Arrays.stream(responseEntity).filter(x -> x.getId() == 4).findFirst().get();

        //assertion
        assertThat(responseEmployee).isEqualTo(employee);
    }

    @Test
    public void  testPUTEmployee() {

        //Arrange
        Employee employee = Employee.builder()
                .id(3)
                .email("Haguid@anymail.com")
                .phone(7412365)
                .address(Address.builder().city("Chennai").country("India").street("12 Madurai").build())
                .name("Haguid")
                .build();

        //Act
        Response response = given().baseUri(baseUrl)
                .port(port)
                .basePath("/employee/3")
                .contentType("application/json")
                .body(employee)
                .put();

        Employee responseEntity = response.body().as(Employee.class);


        //assertion
        assertThat(responseEntity).isEqualTo(employee);

    }


}
