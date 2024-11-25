package edu.miu.cse.vsms.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "vservices")
@Data
@NoArgsConstructor
public class VService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serviceName;
    private double cost;
    private String vehicleType;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    public VService(String serviceName, double cost, String vehicleType, Employee employee) {
        this.serviceName = serviceName;
        this.cost = cost;
        this.vehicleType = vehicleType;
        this.employee = employee;
    }
}