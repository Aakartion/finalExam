package edu.miu.cse.vsms.dto.response;

public record VehicleServiceResponseDto(
        Long id,
        String serviceName,
        double cost,
        String vehicleType
) {
}
