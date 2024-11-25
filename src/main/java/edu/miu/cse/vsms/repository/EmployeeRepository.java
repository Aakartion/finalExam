package edu.miu.cse.vsms.repository;

import edu.miu.cse.vsms.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findEmployeeByEmail(String email);

    Employee findEmployeeById(Long id);
}
