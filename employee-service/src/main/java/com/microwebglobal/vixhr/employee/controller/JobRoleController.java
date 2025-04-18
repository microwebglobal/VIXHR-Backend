package com.microwebglobal.vixhr.employee.controller;

import com.microwebglobal.vixhr.employee.model.JobRole;
import com.microwebglobal.vixhr.employee.service.JobRoleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/job-roles")
@SecurityRequirement(name = "oauth")
public class JobRoleController {

    private final JobRoleService jobRoleService;

    @GetMapping("/company/{companyId}")
    public Iterable<JobRole> getAllJobRolesByCompany(@PathVariable Long companyId) {
        return jobRoleService.getAllJobRolesByCompany(companyId);
    }

    @GetMapping("/{id}")
    public JobRole getJobRoleById(@PathVariable Long id) {
        return jobRoleService.getJobRoleById(id);
    }

    @PostMapping
    public JobRole createJobRole(@RequestBody JobRole jobRole) {
        return jobRoleService.createJobRole(jobRole);
    }

    @PutMapping("/{id}")
    public JobRole updateJobRole(@PathVariable Long id, @RequestBody JobRole jobRole) {
        return jobRoleService.updateJobRole(id, jobRole);
    }

    @DeleteMapping("/{id}")
    public void deleteJobRole(@PathVariable Long id) {
        jobRoleService.deleteJobRole(id);
    }
}
