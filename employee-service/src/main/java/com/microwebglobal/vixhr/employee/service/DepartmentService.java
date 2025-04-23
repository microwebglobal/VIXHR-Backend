package com.microwebglobal.vixhr.employee.service;

import com.microwebglobal.vixhr.employee.client.CompanyClient;
import com.microwebglobal.vixhr.employee.dto.department.DepartmentRequest;
import com.microwebglobal.vixhr.employee.model.Department;
import com.microwebglobal.vixhr.employee.repository.DepartmentRepository;
import com.microwebglobal.vixhr.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final CompanyClient companyClient;
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public Iterable<Department> getAllDepartmentsByCompany(Long companyId) {
        return departmentRepository.findAllByCompanyId(companyId);
    }

    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found for ID: " + id));
    }

    public Department createDepartment(DepartmentRequest request) {
        companyClient.getCompanyById(request.getCompanyId());

        var department = request.toDepartment();
        if (request.getManagerId() != null){
            var manager = employeeRepository.findById(request.getManagerId())
                    .orElseThrow(() -> new RuntimeException("Manager not found for ID: " + request.getManagerId()));

            department.setManager(manager);
        }

        return departmentRepository.save(department);
    }

    public Department updateDepartment(Long id, DepartmentRequest request) {
        departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found for ID: " + id));

        companyClient.getCompanyById(request.getCompanyId());

        var department = request.toDepartment();
        if (request.getManagerId() != null){
            var manager = employeeRepository.findById(request.getManagerId())
                    .orElseThrow(() -> new RuntimeException("Manager not found for ID: " + request.getManagerId()));

            department.setManager(manager);
        }

        department.setId(id);
        return departmentRepository.save(department);
    }

    public void deleteDepartment(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found for ID: " + id));

        departmentRepository.delete(department);
    }
}
