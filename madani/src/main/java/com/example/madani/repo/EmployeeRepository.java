package com.example.madani.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
 
import com.example.madani.model.Employee;
 
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
	List<Employee> findByName(String name);
}
