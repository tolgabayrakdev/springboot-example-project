package com.tolgabayrak.deneme.service;

import com.tolgabayrak.deneme.dto.EmployeeDto;
import com.tolgabayrak.deneme.model.Employee;
import com.tolgabayrak.deneme.repository.EmployeeRepository;
import com.tolgabayrak.deneme.util.Helper;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    private final ModelMapper modelMapper;

    private final Helper helper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper, Helper helper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
        this.helper = helper;
    }

    public Employee create(Employee employeeData){
        Employee newEmployee = new Employee(employeeData.getId(),employeeData.getName());
        return employeeRepository.save(newEmployee);
    }

    public EmployeeDto show(Long id){
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()){
            return modelMapper.map(employee, EmployeeDto.class);
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Employee not found");
        }

    }

}
