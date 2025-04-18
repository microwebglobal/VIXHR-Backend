package com.microwebglobal.vixhr.company.services;

import com.microwebglobal.vixhr.company.dto.CompanySettingRequest;
import com.microwebglobal.vixhr.company.models.CompanySetting;
import com.microwebglobal.vixhr.company.repository.CompanyRepository;
import com.microwebglobal.vixhr.company.repository.CompanySettingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanySettingService {

    private final CompanyRepository companyRepository;
    private final CompanySettingRepository companySettingRepository;

    public Iterable<CompanySetting> getSettingsByCompany(Long companyId) {
        return companySettingRepository.findAllByCompanyId(companyId);
    }

    public CompanySetting save(CompanySettingRequest request) {
        var companySetting = request.toCompanySetting();

        var company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() -> new RuntimeException("Company not found for ID: " + request.getCompanyId()));

        companySetting.setCompany(company);
        return companySettingRepository.save(companySetting);
    }

    public CompanySetting update(Long id, CompanySettingRequest request) {
        companySettingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company Setting not found for ID: " + id));

        var companySetting = request.toCompanySetting();

        var company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() -> new RuntimeException("Company not found for ID: " + request.getCompanyId()));

        companySetting.setId(id);
        companySetting.setCompany(company);
        return companySettingRepository.save(companySetting);
    }

    public void delete(Long id) {
        var companySetting = companySettingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company Setting not found for ID: " + id));

        companySettingRepository.delete(companySetting);
    }
}
