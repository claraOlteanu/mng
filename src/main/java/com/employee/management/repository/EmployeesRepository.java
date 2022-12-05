package com.employee.management.repository;

import com.employee.management.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeesRepository extends MongoRepository<Employee,String> {
    List<Employee> findEmployeeByName(String name);

    Optional<Employee> findById(Long id);

    void deleteById(Long id);

//    Employee updateEmployee(String id, Employee employeeDetails);


}
