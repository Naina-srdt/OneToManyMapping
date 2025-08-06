package com.example.OneToManyMapping.Repository;

import com.example.OneToManyMapping.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

    List<Employee> findByAge(int age);

    Optional<Employee> findById(Long id);
}
