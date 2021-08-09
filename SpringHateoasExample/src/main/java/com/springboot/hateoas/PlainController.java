package com.springboot.hateoas;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlainController {

	private final EmployeeService employeeService;

	public PlainController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping("/plain/employees")
	public List<Employee> all() {
		return this.employeeService.findAll();
	}

	@PostMapping("/plain/employees")
	public Employee create(@RequestBody Employee newEmployee) {
		return this.employeeService.create(newEmployee);
	}

	@GetMapping("/plain/employees/{id}")
	public Employee single(@PathVariable int id) {
		return this.employeeService.findById(id);
	}

	@PutMapping("/plain/employees/{id}")
	public Employee update(@RequestBody Employee updatedEmployee, @PathVariable int id) {
		return this.employeeService.replace(updatedEmployee, id);
	}

}
