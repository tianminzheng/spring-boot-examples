package com.springboot.hateoas;

class EmployeeNotFound extends RuntimeException {

	private EmployeeNotFound(String message) {
		super(message);
	}

	static EmployeeNotFound byName(String name) {
		return new EmployeeNotFound("Unable to find employee with name '" + name + "'");
	}

	static EmployeeNotFound byRole(String role) {
		return new EmployeeNotFound("Unable to find employee with role '" + role + "'");
	}
}
