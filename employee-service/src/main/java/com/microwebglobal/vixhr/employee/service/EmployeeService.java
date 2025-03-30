package com.microwebglobal.vixhr.employee.service;

import com.microwebglobal.vixhr.employee.model.Employee;
import com.microwebglobal.vixhr.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public Employee getEmployeeByUserId(Long userId) {
        Employee employee = employeeRepository.findByUserId(userId);
        if (employee == null) {
            throw new RuntimeException("Employee not found for user ID: " + userId);
        }
        return employee;
    }
}