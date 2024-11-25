package edu.miu.cse.vsms.dto.request;

public record ServiceRequestDto(
        Long employeeId,
        String serviceName,
        double cost,
        String vehicleType
) {
}
