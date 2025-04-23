package com.microwebglobal.vixhr.attendance.client;

import com.microwebglobal.vixhr.attendance.dto.EmployeeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class EmployeeClient {

    private final RestClient restClient;

    public EmployeeResponse getEmployeeById(Long id) {
        return restClient.get()
                .uri("http://employee-service/api/employees/" + id)
                .retrieve()
                .body(EmployeeResponse.class);
    }
}
