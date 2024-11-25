package edu.miu.cse.vsms.repository;

import edu.miu.cse.vsms.model.Employee;
import edu.miu.cse.vsms.model.VService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleServiceRepository extends JpaRepository<VService,Long> {

    Employee findVServiceByEmployeeId(long id);
}
