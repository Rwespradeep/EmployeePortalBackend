package com.example.mngmt.employeePortal.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mngmt.employeePortal.exception.ResourceNotFoundException;
import com.example.mngmt.employeePortal.model.Employee;
import com.example.mngmt.employeePortal.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:5173")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	//getallemployess
	@GetMapping("/employees")
	public List<Employee> getAllemployees(){
		return employeeRepository.findAll();
	}
	
	//creatinEmployee	
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	
	//getEmployee by id
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeByid(@PathVariable Long id) {
		Employee emp = employeeRepository.findById(id).
				orElseThrow(() -> 
				new ResourceNotFoundException("Employee does not exist with the id: " + id));
		return ResponseEntity.ok(emp);
	}
	
	//updateEmployee
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee empDetails){
		Employee emp = employeeRepository.findById(id).
				orElseThrow(() -> 
				new ResourceNotFoundException("Employee does not exist with the id: " + id));
		
		emp.setFirstName(empDetails.getFirstName());
		emp.setLastName(empDetails.getLastName());
		emp.setEmailId(empDetails.getEmailId());
		
		Employee updatedEmployee = employeeRepository.save(emp);
		 
		
		return ResponseEntity.ok(updatedEmployee);
	}
	
	//deleteEmployee
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
		Employee emp = employeeRepository.findById(id).
				orElseThrow(() -> 
				new ResourceNotFoundException("Employee does not exist with the id: " + id));
		employeeRepository.delete(emp);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", true);
		
		return ResponseEntity.ok(response);
	}
	
}
