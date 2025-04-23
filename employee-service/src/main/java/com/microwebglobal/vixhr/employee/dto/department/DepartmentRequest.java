package com.microwebglobal.vixhr.employee.dto.department;

import com.microwebglobal.vixhr.employee.model.Department;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentRequest {

    @NotNull
    @Positive
    private Long companyId;

    @NotNull
    @Length(min = 2, max = 50)
    private String name;

    @NotNull
    @Length(min = 2, max = 50)
    private String description;

    @Positive
    private Long managerId;

    public Department toDepartment() {
        Department department = new Department();
        department.setName(this.name);
        department.setCompanyId(this.companyId);
        department.setDescription(this.description);
        return department;
    }
}
