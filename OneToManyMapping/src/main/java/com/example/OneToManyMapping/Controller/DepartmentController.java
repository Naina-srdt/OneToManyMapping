package com.example.OneToManyMapping.Controller;


import com.example.OneToManyMapping.Entity.Department;
import com.example.OneToManyMapping.Repository.DepartmentRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {

    private final DepartmentRepo departmentRepo;

    public DepartmentController(DepartmentRepo departmentRepo) {
        this.departmentRepo = departmentRepo;
    }

    @PostMapping
    public Department createDepartment(@RequestBody Department department) {
        department.getEmployees().forEach(e -> e.setDepartment(department));
        return departmentRepo.save(department);
    }

    //Get Department by
    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartment(@PathVariable Long id) {
        return departmentRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
