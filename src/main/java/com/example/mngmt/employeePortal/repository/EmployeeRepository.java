package com.example.mngmt.employeePortal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mngmt.employeePortal.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
