
package com.microwebglobal.vixhr.attendance.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.microwebglobal.vixhr.common.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "attendance_records",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"employee_id", "date"})
        }
)
public class AttendanceRecord extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull
    @Column(name = "employee_id", nullable = false)
    private Long employeeId;

    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @NotNull
    private LocalTime checkInTime;

    @NotNull
    private LocalTime checkOutTime;

    private double checkInLongitude;
    private double checkInLatitude;
    private double checkoutLongitude;
    private double checkoutLatitude;

    @Length(min = 2, max = 100)
    private String checkInAddress;

    @Length(min = 2, max = 100)
    private String checkoutAddress;

    @Length(min = 2, max = 100)
    private String checkInDeviceId;

    @Length(min = 2, max = 100)
    private String checkoutDeviceId;

    @Length(min = 45, max = 45)
    private String checkInIp;

    @Length(min = 45, max = 45)
    private String checkoutIp;

    @NotNull
    @Length(min = 2, max = 20)
    private String status;

    private boolean isOvertime = false;

    @DecimalMin("0")
    private double overtimeHours;

    @Length(min = 2, max = 500)
    private String notes;
}