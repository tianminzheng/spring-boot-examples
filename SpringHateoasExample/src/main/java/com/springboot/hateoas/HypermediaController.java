package com.springboot.hateoas;

import org.springframework.hateoas.Affordance;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
public class HypermediaController {

	private final EmployeeService employeeService;
	private final HypermediaEmployeeAssembler assembler;

	public HypermediaController(EmployeeService employeeService, HypermediaEmployeeAssembler assembler) {

		this.employeeService = employeeService;
		this.assembler = assembler;
	}

	@GetMapping("/hypermedia/employees")
	public CollectionModel<EntityModel<Employee>> all() {
		return assembler.toCollectionModel(employeeService.findAll());
	}

	@PostMapping("/hypermedia/employees")
	public EntityModel<Employee> create(@RequestBody Employee newEmployee) {
		return assembler.toModel(employeeService.create(newEmployee));
	}

	@GetMapping("/hypermedia/employees/{id}")
	public EntityModel<Employee> single(@PathVariable int id) {
		Link selfLink = linkTo(methodOn(HypermediaController.class).single(id)).withSelfRel();
		Affordance update = afford(methodOn(HypermediaController.class).update(null, id));
		Link aggregateRoot = linkTo(methodOn(HypermediaController.class).all()).withRel("employees");

		return EntityModel.of(employeeService.findById(id), selfLink.andAffordance(update), aggregateRoot);
	}

	@PutMapping("/hypermedia/employees/{id}")
	public EntityModel<Employee> update(@RequestBody Employee updatedEmployee, @PathVariable int id) {
		return assembler.toModel(employeeService.replace(updatedEmployee, id));
	}

}
