package com.springboot.hateoas;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

	private static final List<Employee> EMPLOYEES = new ArrayList<>();

	private EmployeeService() {

		create(new Employee("FirstName1", "LastName1", "USER"));
		create(new Employee("FirstName2", "LastName2", "ADMIN"));
	}

	public List<Employee> findAll() {
		return EMPLOYEES;
	}
	
	public Employee findById(int id) {
		return EMPLOYEES.get(id);
	}

	public Employee findByName(String firstName, String lastName) {

		return EMPLOYEES.stream() 
				.filter(employee -> employee.getFirstName().equals(firstName) && employee.getLastName().equals(lastName)) //
				.findFirst() 
				.orElseThrow(() -> EmployeeNotFound.byName(firstName + " " + lastName));
	}

	public Employee findByRole(String role) {

		return EMPLOYEES.stream() 
				.filter(employee -> employee.getRole().equals(role)) 
				.findFirst() 
				.orElseThrow(() -> EmployeeNotFound.byRole(role));
	}

	public Employee create(Employee newEmployee) {

		Employee newlyCreatedEmployee = new Employee(EMPLOYEES.size(), newEmployee.getFirstName(),
				newEmployee.getLastName(), newEmployee.getRole());
		EMPLOYEES.add(newlyCreatedEmployee);
		return newlyCreatedEmployee;
	}

	public Employee replace(Employee updatedEmployee, int id) {

		EMPLOYEES.remove(id);
		EMPLOYEES.add(id, updatedEmployee);
		return findById(id);
	}
}
