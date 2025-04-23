package com.microwebglobal.vixhr.company.controllers;

import com.microwebglobal.vixhr.company.models.Company;
import com.microwebglobal.vixhr.company.services.CompanyService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/companies")
@SecurityRequirement(name = "oauth")
class CompanyController {

    private final CompanyService companyService;

    @GetMapping
    public Iterable<Company> getCompanies() {
        return companyService.getCompanies();
    }

    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable Long id) {
        return companyService.getCompanyById(id);
    }

    @PostMapping
    public Company createCompany(@RequestBody @Valid Company company) {
        return companyService.createCompany(company);
    }

    @PutMapping("/{id}")
    public Company updateCompany(@PathVariable Long id, @RequestBody @Valid Company company) {
        return companyService.updateCompany(id, company);
    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
    }
}
