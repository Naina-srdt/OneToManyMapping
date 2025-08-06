package com.example.OneToManyMapping.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "employee")
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long emp_id;

    @Column(nullable = false)
    private String name;

    private int age;

    @ManyToOne
    @JoinColumn(name = "department_id")
    //need to explore
    @JsonIgnore
    private Department department;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private List<Address> addresses;

}
