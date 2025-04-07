package com.microwebglobal.vixhr.employee.service;

import com.microwebglobal.vixhr.employee.model.Employee;
import com.microwebglobal.vixhr.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

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

    public Employee updateEmployee(Long id, Employee employee) {
        employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found for ID: " + employee.getId()));

        employee.setId(id);
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found for ID: " + id));

        employeeRepository.delete(employee);
    }
}