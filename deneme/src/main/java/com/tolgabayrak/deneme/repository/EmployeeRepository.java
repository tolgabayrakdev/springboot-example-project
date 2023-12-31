package com.tolgabayrak.deneme.repository;

import com.tolgabayrak.deneme.model.Employee;
import com.tolgabayrak.deneme.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<User> findByEmail(String email);
}
