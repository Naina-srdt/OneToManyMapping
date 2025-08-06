package com.example.OneToManyMapping.Controller;

import com.example.OneToManyMapping.Entity.Employee;
import com.example.OneToManyMapping.Repository.EmployeeRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

       private final EmployeeRepo employeeRepo;

    public EmployeeController(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    //Get All Employees
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    //Get Employee By id
    //For Check
    @GetMapping("/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable Long id){
         return employeeRepo.findById(id);
    }

    //Add an Employee
    @PostMapping("/add")
    public Employee addEmployee(@RequestBody Employee employee){
        return employeeRepo.save(employee);
    }

    //Get Employee By age
    @GetMapping("/age/{age}")
    public List<Employee> getEmployeesByAge(@PathVariable int age) {
        return employeeRepo.findByAge(age);
    }

    //Update the details of Employee
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmpData) {
        return employeeRepo.findById(id).map(emp -> {
            emp.setName(updatedEmpData.getName());
            emp.setAge(updatedEmpData.getAge());
            emp.setAddresses(updatedEmpData.getAddresses());
            emp.setDepartment(updatedEmpData.getDepartment());
            emp.setEmp_id(updatedEmpData.getEmp_id());
            return ResponseEntity.ok(employeeRepo.save(emp));
        }).orElse(ResponseEntity.notFound().build());
    }
}
