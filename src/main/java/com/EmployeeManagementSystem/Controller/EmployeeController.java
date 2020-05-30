package com.EmployeeManagementSystem.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.EmployeeManagementSystem.Model.AuthenticationRequest;
import com.EmployeeManagementSystem.Model.Employee;
import com.EmployeeManagementSystem.Service.EmployeeService;
import com.EmployeeManagementSystem.Util.JwtUtil;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	final static Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	EmployeeService employeeservice;

	/**
	 * This method return employee by Id
	 * 
	 * @param id
	 * @return Employee
	 */
	@GetMapping("/{id}")
	public Employee getEmployeeById(@PathVariable int id) {
		LOGGER.info("Inside getOneEmployee()");
		return employeeservice.getOneEmployee(id);
	}

	/**
	 * This method creates new employee record in database.
	 * 
	 * @param employee
	 * @return Employee
	 */
	@PostMapping("")
	public Employee saveEmployee(@RequestBody Employee employee) {
		LOGGER.info("Inside saveEmployee()");
		return employeeservice.saveEmployee(employee);
	}

	/**
	 * This method updates existing employee by Id
	 * 
	 * @param employee
	 * @param id
	 * @return Employee
	 */
	@PutMapping("/{id}")
	public Employee updateEmployeeById(@RequestBody Employee employee, @PathVariable int id) {
		LOGGER.info("Inside updateEmployeeById()");
		return employeeservice.putOneEmployee(employee, id);
	}

	/**
	 * This method deletes existing employee by Id
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEmployeeById(@PathVariable int id) {
		LOGGER.info("Inside deleteEmployeeById()");
		employeeservice.deleteEmployeeByID(id);
		return ResponseEntity.ok("Entity deleted Sucessfully");
	}

	/**
	 * This method gets list of all employees from database filters records If name
	 * is provided as part of query parameters.
	 * 
	 * @return List<Employee>
	 */
	@GetMapping("")
	public List<Employee> getEmployees(@RequestParam(required = false) String name,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int limit,
			@RequestParam(defaultValue = "firstName") String sortKey,
			@RequestParam(defaultValue = "asc") String sortOrder) {
		if (name != null) {
			return employeeservice.getFilteredEmployees(name, page, limit, sortKey, sortOrder);
		}
		return employeeservice.findPaginated(page, limit, sortKey, sortOrder);
	}
}
