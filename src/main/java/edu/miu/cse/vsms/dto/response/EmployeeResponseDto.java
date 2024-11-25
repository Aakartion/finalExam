package edu.miu.cse.vsms.dto.response;

import java.time.LocalDate;
import java.util.List;

public record EmployeeResponseDto(
        Long id,
        String name,
        String email,
        String phone,
        LocalDate hireDate,
        List<VehicleServiceResponseDto> services
) {
}
