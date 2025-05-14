package com.microwebglobal.vixhr.attendance.client;

import com.microwebglobal.vixhr.attendance.dto.CompanySettingsResponse;
import com.microwebglobal.vixhr.attendance.dto.OvertimePolicyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CompanyClient {

    private final RestClient restClient;

    public OvertimePolicyResponse getOvertimePolicyByCompany(Long id) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("http")
                        .host("company-service")
                        .path("/api/overtime-policies/company/{id}")
                        .queryParam("date", LocalDate.now())
                        .build(id))
                .retrieve()
                .body(OvertimePolicyResponse.class);
    }

    public CompanySettingsResponse getCompanySettingsByCompany(Long id) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("http")
                        .host("company-service")
                        .path("/api/company-settings/company/{id}")
                        .queryParam("date", LocalDate.now())
                        .build(id))
                .retrieve()
                .body(CompanySettingsResponse.class);
    }
}
