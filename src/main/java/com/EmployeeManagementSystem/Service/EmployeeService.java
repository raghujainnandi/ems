package com.EmployeeManagementSystem.Service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.EmployeeManagementSystem.Controller.EmployeeController;
import com.EmployeeManagementSystem.Exception.NotFoundException;
import com.EmployeeManagementSystem.Model.Employee;
import com.EmployeeManagementSystem.Repository.EmployeeRepository;

@Service
public class EmployeeService implements IEmployeeService {

	final static Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	EmployeeRepository employeerepository;

	/**
	 * This method return all the employees.
	 * 
	 * @return List<Employee>
	 */
	public List<Employee> getAllEmployees() {
		LOGGER.info("Inside Repository  getAllEmployees()");
		List<Employee> employees = employeerepository.findAll();
		return employees;

	}

	/**
	 * This method returns employee by id
	 * 
	 * @param id
	 * @return Employee
	 */
	public Employee getOneEmployee(int id) {
		LOGGER.info("Inside Repository getOneEmployee()");
		return employeerepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Employee %d not found", id)));
	}

	/**
	 * This method saves employee
	 * 
	 * @param employee
	 * @return Employee
	 */
	public Employee saveEmployee(Employee employee) {
		LOGGER.info("Inside Repository saveEmployee()");
		return employeerepository.save(employee);
	}

	/**
	 * This method updates employee by id
	 * 
	 * @param employee
	 * @param id
	 * @return Employee
	 */
	public Employee putOneEmployee(Employee employee, int id) {
		employeerepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Employee %d not found", id)));
		employeerepository.save(employee);
		return employee;
	}

	/**
	 * This method deletes employee by id
	 * 
	 * @param id
	 */
	public void deleteEmployeeByID(int id) {
		LOGGER.info("Inside Repository deleteEmployee() ");
		employeerepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Employee %d not found", id)));
		employeerepository.deleteById(id);
	}

	/**
	 * This method takes the below query parameters
	 * 
	 * @param name
	 * @param page
	 * @param limit
	 * @param sortKey
	 * @param sortOrder
	 * @return List<Employee>
	 */
	public List<Employee> getFilteredEmployees(String name, int page, int limit, String sortKey, String sortOrder) {
		LOGGER.info("Inside Repository  getFilteredEmployees()");
		Sort sorted = Sort.by(sortKey).ascending();
		if (sortOrder.equals("desc")) {
			sorted = Sort.by(sortKey).descending();
		}
		PageRequest paging = PageRequest.of(page, limit, sorted);
		Page<Employee> pagedResult = employeerepository.findByfirstNameContaining(name, paging);
		return pagedResult.toList();
	}

	/**
	 * This method takes the below query parameter
	 * 
	 * @param page
	 * @param limit
	 * @param sortKey
	 * @param sortOrder
	 * @return List<Employee>
	 */
	public List<Employee> findPaginated(int page, int limit, String sortKey, String sortOrder) {
		Sort sorted = Sort.by(sortKey).ascending();
		if (sortOrder.equals("desc")) {
			sorted = Sort.by(sortKey).descending();
		}
		PageRequest paging = PageRequest.of(page, limit, sorted);
		Page<Employee> pagedResult = employeerepository.findAll(paging);
		return pagedResult.toList();

	}
}
