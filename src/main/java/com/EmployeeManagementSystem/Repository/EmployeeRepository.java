package com.EmployeeManagementSystem.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.EmployeeManagementSystem.Model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	Page findByfirstNameContaining(String name, Pageable pageable);

	Optional<Employee> findById(Long id);
	
	public Page findAll(Pageable pageable);
}
