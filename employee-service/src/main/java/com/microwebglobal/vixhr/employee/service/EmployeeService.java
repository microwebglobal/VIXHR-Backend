package com.microwebglobal.vixhr.employee.service;

import com.microwebglobal.vixhr.common.events.EmployeeUpdatedEvent;
import com.microwebglobal.vixhr.employee.client.CompanyClient;
import com.microwebglobal.vixhr.employee.dto.employee.EmployeeRequest;
import com.microwebglobal.vixhr.employee.model.Employee;
import com.microwebglobal.vixhr.employee.repository.DepartmentRepository;
import com.microwebglobal.vixhr.employee.repository.EmployeeRepository;
import com.microwebglobal.vixhr.employee.repository.JobRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final CompanyClient companyClient;
    private final JobRoleRepository jobRoleRepository;
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final KafkaTemplate<String, EmployeeUpdatedEvent> kafka;

    public Employee createEmployee(EmployeeRequest request) {
        var employee = request.toEmployee();
        companyClient.getCompanyById(request.getCompanyId());

        var department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found for ID: " + request.getDepartmentId()));

        var jobRole = jobRoleRepository.findById(request.getJobRoleId())
                .orElseThrow(() -> new RuntimeException("Job role not found for ID: " + request.getJobRoleId()));

        employee.setJobRole(jobRole);
        employee.setDepartment(department);
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

    public Employee updateEmployee(Long id, EmployeeRequest request) {
        employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found for ID: " + id));

        companyClient.getCompanyById(request.getCompanyId());
        var employee = request.toEmployee();

        var department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found for ID: " + request.getDepartmentId()));

        var jobRole = jobRoleRepository.findById(request.getJobRoleId())
                .orElseThrow(() -> new RuntimeException("Job role not found for ID: " + request.getJobRoleId()));

        employee.setId(id);
        employee.setJobRole(jobRole);
        employee.setDepartment(department);
        var saved = employeeRepository.save(employee);

        EmployeeUpdatedEvent ev = new EmployeeUpdatedEvent(
                saved.getId(),
                saved.getCompanyId(),
                saved.getEmployeeCode(),
                saved.getFirstName() + " " + saved.getLastName(),
                saved.getDepartment().getName(),
                saved.getJobRole().getTitle(),
                saved.getBaseSalary()
        );

        kafka.send("employee.updated", ev);
        return saved;
    }

    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found for ID: " + id));

        employeeRepository.delete(employee);
    }
}