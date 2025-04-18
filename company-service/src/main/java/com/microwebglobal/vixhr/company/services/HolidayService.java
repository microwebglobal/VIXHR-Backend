package com.microwebglobal.vixhr.company.services;

import com.microwebglobal.vixhr.company.dto.HolidayRequest;
import com.microwebglobal.vixhr.company.models.Holiday;
import com.microwebglobal.vixhr.company.repository.CompanyRepository;
import com.microwebglobal.vixhr.company.repository.HolidayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HolidayService {

    private final CompanyRepository companyRepository;
    private final HolidayRepository holidayRepository;

    public Iterable<Holiday> getHolidaysByCompany(Long companyId) {
        return holidayRepository.findAllByCompanyId(companyId);
    }

    public Holiday createHoliday(HolidayRequest request) {
        var holiday = request.toHoliday();

        var company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() -> new RuntimeException("Company not found for ID: " + request.getCompanyId()));

        holiday.setCompany(company);
        return holidayRepository.save(holiday);
    }

    public Holiday updateHoliday(Long id, HolidayRequest request) {
        holidayRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Holiday not found for ID: " + id));

        var holiday = request.toHoliday();

        var company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() -> new RuntimeException("Company not found for ID: " + request.getCompanyId()));

        holiday.setId(id);
        holiday.setCompany(company);
        return holidayRepository.save(holiday);
    }

    public void deleteHoliday(Long id) {
        var holiday = holidayRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Holiday not found for ID: " + id));

        holidayRepository.delete(holiday);
    }
}
