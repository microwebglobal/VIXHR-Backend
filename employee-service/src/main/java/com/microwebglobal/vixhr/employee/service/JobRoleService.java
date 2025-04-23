package com.microwebglobal.vixhr.employee.service;

import com.microwebglobal.vixhr.employee.client.CompanyClient;
import com.microwebglobal.vixhr.employee.model.JobRole;
import com.microwebglobal.vixhr.employee.repository.JobRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobRoleService {

    private final CompanyClient companyClient;
    private final JobRoleRepository jobRoleRepository;

    public Iterable<JobRole> getAllJobRolesByCompany(Long companyId) {
        return jobRoleRepository.findAllByCompanyId(companyId);
    }

    public JobRole getJobRoleById(Long id) {
        return jobRoleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job Role not found for ID: " + id));
    }

    public JobRole createJobRole(JobRole jobRole) {
        companyClient.getCompanyById(jobRole.getCompanyId());
        return jobRoleRepository.save(jobRole);
    }

    public JobRole updateJobRole(Long id, JobRole jobRole) {
        jobRoleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job Role not found for ID: " + id));

        companyClient.getCompanyById(jobRole.getCompanyId());
        jobRole.setId(id);
        return jobRoleRepository.save(jobRole);
    }

    public void deleteJobRole(Long id) {
        JobRole jobRole = jobRoleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job Role not found for ID: " + id));

        jobRoleRepository.delete(jobRole);
    }
}
