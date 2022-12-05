package com.employee.management.controller;

import com.employee.management.model.Employee;
import com.employee.management.service.EmployeeService;
import com.employee.management.service.EmployeeServiceImpl;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    private EmployeeController empC;
    private EmployeeService empS;
    private Employee employeeMock = new Employee(12L, "John", "john@gmail.com", 1000, "12334567899", new Date(), "Sales");
    private Employee postEmployeeMock = new Employee();
    @Autowired
    protected MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        empS = mock(EmployeeServiceImpl.class);
        empC = new EmployeeController(empS);
    }

    @Nested
    public class GetEmployeeTest{
        @BeforeEach
        void setUp() {
        }

        @Test
        @DisplayName("Should return correct response when the input parameter is valid")
//        void ValidInputTest(){
//            long testId = 3;
//            HttpHeaders headers = new HttpHeaders();
//            headers.add("Content-Type", "application/json");
//            ResponseEntity<Employee> res = new ResponseEntity<>(employeeMock, headers, HttpStatus.OK);
//            when(empS.findById(testId)).thenReturn(employeeMock);
//
//            assertEquals(empC.getEmployee(testId), res);
//        }
        public void validGetEmployee() throws Exception {
            long testId = 3;
            when(empS.findById(testId)).thenReturn(employeeMock);
            mockMvc.perform(MockMvcRequestBuilders
                            .get("/api/employees/" + testId)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }
    }



    @Nested
    public class PostEmployeeTest{
        @BeforeEach
        void setUp() {
            postEmployeeMock.setDepartment("Sales");
            postEmployeeMock.setEmail("john@gmail.com");
            postEmployeeMock.setDateOfBirth(new Date());
            postEmployeeMock.setName("John");
            postEmployeeMock.setSalary(1000);
            postEmployeeMock.setTelephone("12334567899");
        }

        @Test
        @DisplayName("Should return correct employee details when a valid employee is provided")
        void ValidInputTest(){
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json");
            Gson gson = new Gson();
            ResponseEntity<String> res = new ResponseEntity<>(gson.toJson(employeeMock), headers, HttpStatus.CREATED);
            when(empS.save(postEmployeeMock)).thenReturn(employeeMock);

            assertEquals(res, empC.postEmployee(postEmployeeMock));
        }
    }

    @Nested
    public class PutEmployeeTest{
        @BeforeEach
        void setUp() {
        }

        @Test
        @DisplayName("Should return correct employee details when a valid employee and id are provided")
        void ValidInputTest(){
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json");
            headers.add(HttpHeaders.LOCATION, "/api/employees" + employeeMock.getId());
            Gson gson = new Gson();
            ResponseEntity<String> res = new ResponseEntity<>(gson.toJson(employeeMock), headers, HttpStatus.CREATED);
            when(empS.save(employeeMock)).thenReturn(employeeMock);

            assertEquals(res, empC.putEmployee(employeeMock, employeeMock.getId()));
        }
    }

    @Nested
    public class DeleteEmployeeTest{
        @BeforeEach
        void setUp() {
        }

        @Test
        @DisplayName("Should delete correct employee details when a valid id is provided")
        void ValidInputTest(){
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json");
            ResponseEntity<String> res = new ResponseEntity<>("employee deleted", headers, HttpStatus.OK);
//            doNothing().when(empS).deleteById(3L);

            assertEquals(res, empC.deleteEmployee(employeeMock.getId()));
        }
    }
}