package edu.miu.cse.vsms.service;

import edu.miu.cse.vsms.dto.request.ServiceRequestDto;
import edu.miu.cse.vsms.dto.response.VehicleServiceResponseDto;

public interface VehicleService {
    VehicleServiceResponseDto assignService(ServiceRequestDto request);
}
