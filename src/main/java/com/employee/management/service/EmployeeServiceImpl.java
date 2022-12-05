package com.employee.management.service;

import com.employee.management.exception.EmployeeNotFoundException;
import com.employee.management.exception.NoDataFoundException;
import com.employee.management.model.Employee;
import org.springframework.stereotype.Service;
import com.employee.management.repository.EmployeesRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeesRepository employeeRepository;

    public EmployeeServiceImpl(EmployeesRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee save(Employee f) {
        return employeeRepository.save(f);
    }

    @Override
    public List<Employee> findAll() {
        var employees = (List<Employee>) employeeRepository.findAll();
        if(employees.isEmpty()) {
            throw new NoDataFoundException();
        }
        return employees;
    }

    @Override
    public List<Employee> findEmployeeByName(String name) {
        return employeeRepository.findEmployeeByName(name);
    }

    @Override
    public Employee findById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @Override
    public void deleteById(Long id){
        this.employeeRepository.deleteById(id);
        }

}
