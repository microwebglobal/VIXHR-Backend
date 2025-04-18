package com.microwebglobal.vixhr.employee.controller;

import com.microwebglobal.vixhr.employee.dto.department.DepartmentRequest;
import com.microwebglobal.vixhr.employee.model.Department;
import com.microwebglobal.vixhr.employee.service.DepartmentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/departments")
@SecurityRequirement(name = "oauth")
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping("/company/{companyId}")
    public Iterable<Department> getAllDepartmentsByCompany(@PathVariable Long companyId) {
        return departmentService.getAllDepartmentsByCompany(companyId);
    }

    @GetMapping("/{id}")
    public Department getDepartmentById(@PathVariable Long id) {
        return departmentService.getDepartmentById(id);
    }

    @PostMapping
    public Department createDepartment(@RequestBody @Valid DepartmentRequest request) {
        return departmentService.createDepartment(request);
    }

    @PutMapping("/{id}")
    public Department updateDepartment(@PathVariable Long id, @RequestBody @Valid DepartmentRequest request) {
        return departmentService.updateDepartment(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
    }
}
