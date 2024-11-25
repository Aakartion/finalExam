package edu.miu.cse.vsms.service.impl;

import edu.miu.cse.vsms.dto.request.EmployeeRequestDto;
import edu.miu.cse.vsms.dto.response.EmployeeResponseDto;
import edu.miu.cse.vsms.dto.response.VehicleServiceResponseDto;
import edu.miu.cse.vsms.exception.ResourceNotFoundException;
import edu.miu.cse.vsms.model.Employee;
import edu.miu.cse.vsms.repository.EmployeeRepository;
import edu.miu.cse.vsms.service.EmployeeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

  private final EmployeeRepository employeeRepository;

  @Override
  public EmployeeResponseDto addEmployee(EmployeeRequestDto request) throws Exception {

    Employee searchEmployee = employeeRepository.findEmployeeByEmail(request.email());

    if (searchEmployee != null) {
      throw new Exception("Employee already present in the system");
    }

    Employee employee =
        new Employee(request.name(), request.email(), request.phone(), request.hireDate());

    employeeRepository.save(employee);
    EmployeeResponseDto employeeResponseDto = mapToResponseDto(employee);
    return employeeResponseDto;
  }

  @Override
  public List<EmployeeResponseDto> getAllEmployees() {
    return employeeRepository.findAll().stream()
        .map(this::mapToResponseDto)
        .collect(Collectors.toList());
  }

  @Override
  public EmployeeResponseDto getEmployeeById(Long id) {
    Employee employee = employeeRepository.findEmployeeById(id);
    if (employee == null) {
      throw new ResourceNotFoundException("Employee not found");
    }
    EmployeeResponseDto employeeResponseDto = mapToResponseDto(employee);
    return employeeResponseDto;
  }

  @Override
  public EmployeeResponseDto partiallyUpdateEmployee(Long id, Map<String, Object> updates) {
    // Fetch the employee by ID or throw an exception if not found
    Employee employee =
        employeeRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Employee not found with id " + id));

    // Apply each update based on the key
    updates.forEach(
        (key, value) -> {
          switch (key) {
            case "name":
              employee.setName((String) value);
              break;
            case "email":
              employee.setEmail((String) value);
              break;
            case "phone":
              employee.setPhone((String) value);
              break;
            case "hireDate":
              employee.setHireDate((LocalDate) value);

              break;
            default:
              throw new IllegalArgumentException("Invalid field: " + key);
          }
        });
    employeeRepository.save(employee);
    EmployeeResponseDto employeeResponseDto = mapToResponseDto(employee);
    return employeeResponseDto;
  }

  private EmployeeResponseDto mapToResponseDto(Employee employee) {
    List<VehicleServiceResponseDto> serviceDtos =
        employee.getVServices().stream()
            .map(
                service ->
                    new VehicleServiceResponseDto(
                        service.getId(),
                        service.getServiceName(),
                        service.getCost(),
                        service.getVehicleType()))
            .toList();

    return new EmployeeResponseDto(
        employee.getId(),
        employee.getName(),
        employee.getEmail(),
        employee.getPhone(),
        employee.getHireDate(),
        serviceDtos);
  }
}
