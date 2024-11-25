package edu.miu.cse.vsms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.miu.cse.vsms.dto.request.EmployeeRequestDto;
import edu.miu.cse.vsms.dto.response.EmployeeResponseDto;
import edu.miu.cse.vsms.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.List;


@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void addEmployee() throws Exception {
        EmployeeRequestDto employeeRequestDto = new EmployeeRequestDto("Harry Doe", "harry@email.com", "1234567890", LocalDate.of(2021, 01, 9));

        EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto(1L, "Harry Doe", "harry@email.com", "123456789", LocalDate.of(2021,01,9), List.of());

        Mockito.when(employeeService.addEmployee(employeeRequestDto)).thenReturn(employeeResponseDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employeeRequestDto))
        )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(employeeResponseDto)))
                .andDo(MockMvcResultHandlers.print());
    }
}
