package com.springboot.hateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.Affordance;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Service;

@Service
public class HypermediaEmployeeAssembler implements SimpleRepresentationModelAssembler<Employee> {

	@Override
	public void addLinks(EntityModel<Employee> resource) {

		int id = resource.getContent().getId();

		Link selfLink = linkTo(methodOn(HypermediaController.class).single(id)).withSelfRel();
		Affordance update = afford(methodOn(HypermediaController.class).update(null, id));

		resource.add(selfLink.andAffordance(update));

		resource.add(linkTo(methodOn(HypermediaController.class).all()).withRel("employees"));
	}

	@Override
	public void addLinks(CollectionModel<EntityModel<Employee>> resources) {

		resources.add(linkTo(methodOn(HypermediaController.class).all()).withSelfRel() 
				.andAffordance(afford(methodOn(HypermediaController.class).create(null))));

	}
}
