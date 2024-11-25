package edu.miu.cse.vsms.service.impl;

import edu.miu.cse.vsms.dto.request.ServiceRequestDto;
import edu.miu.cse.vsms.dto.response.VehicleServiceResponseDto;
import edu.miu.cse.vsms.exception.ResourceNotFoundException;
import edu.miu.cse.vsms.model.Employee;
import edu.miu.cse.vsms.model.VService;
import edu.miu.cse.vsms.repository.EmployeeRepository;
import edu.miu.cse.vsms.repository.VehicleServiceRepository;
import edu.miu.cse.vsms.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

  private final VehicleServiceRepository vehicleServiceRepository;
  private final EmployeeRepository employeeRepository;

  @Override
  public VehicleServiceResponseDto assignService(ServiceRequestDto request) {

    Employee employee =
        employeeRepository
            .findById(request.employeeId())
            .orElseThrow(
                () -> new ResourceNotFoundException("Employee not found" + request.employeeId()));

    VService vService =
        new VService(request.serviceName(), request.cost(), request.vehicleType(), employee);
    employee.addVService(vService);
    vehicleServiceRepository.save(vService);
    VehicleServiceResponseDto vehicleServiceResponseDto =
        new VehicleServiceResponseDto(
            vService.getId(),
            vService.getServiceName(),
            vService.getCost(),
            vService.getVehicleType());
    return vehicleServiceResponseDto;
  }
}
