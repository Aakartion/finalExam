package edu.miu.cse.vsms.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "employees")
@Data
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;
    private LocalDate hireDate;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.PERSIST)
    private List<VService> vServices = new ArrayList<>();

    public Employee(String name, String email, String phone, LocalDate hireDate) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.hireDate = hireDate;
    }

    public void addVService(VService vService) {
        vServices.add(vService);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", hireDate=" + hireDate +
                ", vServices=" + vServices +
                '}';
    }
}
