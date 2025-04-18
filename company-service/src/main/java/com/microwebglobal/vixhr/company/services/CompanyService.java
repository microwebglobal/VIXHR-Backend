package com.microwebglobal.vixhr.company.services;

import com.microwebglobal.vixhr.company.models.Company;
import com.microwebglobal.vixhr.company.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public Iterable<Company> getCompanies() {
        return companyRepository.findAll();
    }

    public Company getCompanyById(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found for ID: " + id));
    }

    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    public Company updateCompany(Long id, Company company) {
        companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found for ID: " + id));

        company.setId(id);
        return companyRepository.save(company);
    }

    public void deleteCompany(Long id) {
        var company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found for ID: " + id));

        companyRepository.delete(company);
    }
}
