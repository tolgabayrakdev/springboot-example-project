package com.tolgabayrak.deneme.repository;

import com.tolgabayrak.deneme.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
