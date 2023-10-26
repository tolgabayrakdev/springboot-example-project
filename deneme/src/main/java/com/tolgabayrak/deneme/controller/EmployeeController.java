package com.tolgabayrak.deneme.controller;

import com.tolgabayrak.deneme.dto.EmployeeDto;
import com.tolgabayrak.deneme.model.Employee;
import com.tolgabayrak.deneme.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee body){
        return ResponseEntity.ok(employeeService.create(body));
    }

    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable Long id){
        return ResponseEntity.ok(employeeService.show(id));
    }
}
