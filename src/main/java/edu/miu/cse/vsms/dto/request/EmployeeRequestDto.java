package edu.miu.cse.vsms.dto.request;

import java.time.LocalDate;

public record EmployeeRequestDto(
        String name,
        String email,
        String phone,
        LocalDate hireDate
) {
}
