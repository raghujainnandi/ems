package com.EmployeeManagementSystem.Service;

import java.util.List;

import com.EmployeeManagementSystem.Model.Employee;

public interface IEmployeeService {
	
	List<Employee> findPaginated(int page, int limit, String sortKey, String sortOrder);

}
