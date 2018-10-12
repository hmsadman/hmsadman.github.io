package com.example.madani.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
 
import com.example.madani.model.Employee;
import com.example.madani.repo.EmployeeRepository;
 
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class EmployeeController {
 
	@Autowired
	EmployeeRepository repository;
 
	@GetMapping("/employees")
	public List<Employee> getAllemployees() {
		System.out.println("Get all employees...");
 
		List<Employee> employees = new ArrayList<>();
		repository.findAll().forEach(employees::add);
 
		return employees;
	}
 
	@PostMapping(value = "/employees/create")
	public Employee postCustomer(@RequestBody Employee employee) {
 
		Employee _employee = repository.save(new Employee(employee.getName(), employee.getAddress(), employee.getPhone(), employee.getEmail()));
		return _employee;
	}
 
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id) {
		System.out.println("Delete Employee with ID = " + id + "...");
 
		repository.deleteById(id);
 
		return new ResponseEntity<>("Employee has been deleted!", HttpStatus.OK);
	}
 
	@DeleteMapping("/employees/delete")
	public ResponseEntity<String> deleteAllEmployees() {
		System.out.println("Delete All Employees...");
 
		repository.deleteAll();
 
		return new ResponseEntity<>("All Employees have been deleted!", HttpStatus.OK);
	}
 
	@GetMapping(value = "employees/name/{name}")
	public List<Employee> findByName(@PathVariable String name) {
 
		List<Employee> employees = repository.findByName(name);
		return employees;
	}
 
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id, @RequestBody Employee employee) {
		System.out.println("Update Employee with ID = " + id + "...");
 
		Optional<Employee> employeeData = repository.findById(id);
 
		if (employeeData.isPresent()) {
			Employee _employee = employeeData.get();
			_employee.setName(employee.getName());
			_employee.setAddress(employee.getAddress());
			_employee.setPhone(employee.getPhone());
			_employee.setEmail(employee.getEmail());
			return new ResponseEntity<>(repository.save(_employee), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
