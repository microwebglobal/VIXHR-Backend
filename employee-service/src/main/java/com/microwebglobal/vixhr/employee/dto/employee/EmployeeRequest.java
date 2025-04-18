package com.microwebglobal.vixhr.employee.dto.employee;

import com.microwebglobal.vixhr.employee.model.Employee;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequest {

    @NotNull
    @Positive
    private Long userId;

    @NotNull
    @Positive
    private Long companyId;

    @NotNull
    @Length(min = 2, max = 50)
    private String firstName;

    @NotNull
    @Length(min = 2, max = 50)
    private String lastName;

    @NotNull
    @Email
    private String email;

    @Length(min = 10, max = 10)
    private String phone;

    @NotNull
    @Positive
    private Long departmentId;

    @NotNull
    private LocalDate joiningDate;

    private LocalDate terminationDate;

    @Length(min = 2, max = 50)
    private String employeeCode;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private Double baseSalary;

    @NotNull
    @Positive
    private Long jobRoleId;

    public Employee toEmployee() {
        Employee employee = new Employee();
        employee.setUserId(userId);
        employee.setCompanyId(companyId);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setEmail(email);
        employee.setPhone(phone);
        employee.setJoiningDate(joiningDate);
        employee.setTerminationDate(terminationDate);
        employee.setEmployeeCode(employeeCode);
        employee.setBaseSalary(baseSalary);
        return employee;
    }
}
