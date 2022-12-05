package com.employee.management.service;

import com.employee.management.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Employee save(Employee emp);
    List<Employee> findAll();

    List<Employee> findEmployeeByName(String name);

    Employee findById(Long id);

    void deleteById(Long id);

}
