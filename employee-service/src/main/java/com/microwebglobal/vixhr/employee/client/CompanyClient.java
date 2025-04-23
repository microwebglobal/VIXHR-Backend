package com.microwebglobal.vixhr.employee.client;

import com.microwebglobal.vixhr.employee.dto.CompanyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class CompanyClient {

    private final RestClient restClient;

    public CompanyResponse getCompanyById(Long id) {
        return restClient.get()
                .uri("http://company-service/api/companies/" + id)
                .retrieve()
                .body(CompanyResponse.class);
    }
}
