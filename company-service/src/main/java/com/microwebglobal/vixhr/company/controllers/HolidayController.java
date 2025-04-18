package com.microwebglobal.vixhr.company.controllers;

import com.microwebglobal.vixhr.company.dto.HolidayRequest;
import com.microwebglobal.vixhr.company.models.Holiday;
import com.microwebglobal.vixhr.company.services.HolidayService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/holidays")
@SecurityRequirement(name = "oauth")
public class HolidayController {

    private final HolidayService holidayService;

    @GetMapping("/company/{companyId}")
    public Iterable<Holiday> getHolidaysByCompany(@PathVariable Long companyId) {
        return holidayService.getHolidaysByCompany(companyId);
    }

    @PostMapping
    public Holiday createHoliday(@RequestBody @Valid HolidayRequest request) {
        return holidayService.createHoliday(request);
    }

    @PutMapping("/{id}")
    public Holiday updateHoliday(@PathVariable Long id, @RequestBody @Valid HolidayRequest request) {
        return holidayService.updateHoliday(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteHoliday(@PathVariable Long id) {
        holidayService.deleteHoliday(id);
    }
}
