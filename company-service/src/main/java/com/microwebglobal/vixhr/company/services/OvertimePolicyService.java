package com.microwebglobal.vixhr.company.services;

import com.microwebglobal.vixhr.company.dto.OvertimePolicyRequest;
import com.microwebglobal.vixhr.company.models.OvertimePolicy;
import com.microwebglobal.vixhr.company.repository.CompanyRepository;
import com.microwebglobal.vixhr.company.repository.OvertimePolicyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OvertimePolicyService {

    private final CompanyRepository companyRepository;
    private final OvertimePolicyRepository overtimePolicyRepository;

    public Iterable<OvertimePolicy> getOvertimePoliciesByCompany(Long companyId) {
        return overtimePolicyRepository.findAllByCompanyId(companyId);
    }

    public OvertimePolicy createOvertimePolicy(OvertimePolicyRequest request) {
        var overtimePolicy = request.toOvertimePolicy();

        var company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() -> new RuntimeException("Company not found for ID: " + request.getCompanyId()));

        overtimePolicy.setCompany(company);
        return overtimePolicyRepository.save(overtimePolicy);
    }

    public OvertimePolicy updateOvertimePolicy(Long id, OvertimePolicyRequest request) {
        overtimePolicyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Overtime Policy not found for ID: " + id));

        var overtimePolicy = request.toOvertimePolicy();

        var company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() -> new RuntimeException("Company not found for ID: " + request.getCompanyId()));

        overtimePolicy.setId(id);
        overtimePolicy.setCompany(company);
        return overtimePolicyRepository.save(overtimePolicy);
    }

    public void deleteOvertimePolicy(Long id) {
        overtimePolicyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Overtime Policy not found for ID: " + id));

        overtimePolicyRepository.deleteById(id);
    }
}
