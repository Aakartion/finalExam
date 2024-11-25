package edu.miu.cse.vsms.service;

import edu.miu.cse.vsms.dto.request.EmployeeRequestDto;
import edu.miu.cse.vsms.dto.response.EmployeeResponseDto;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    EmployeeResponseDto addEmployee(EmployeeRequestDto request) throws Exception;
    List<EmployeeResponseDto> getAllEmployees();
    EmployeeResponseDto getEmployeeById(Long id);
    EmployeeResponseDto partiallyUpdateEmployee(Long id, Map<String, Object> updates);
}
