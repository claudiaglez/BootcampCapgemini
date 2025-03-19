package com.example.application.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domains.contracts.services.ActoresService;

@RestController
@RequestMapping("/api/actores")
public class ActoresResource {
	
	private ActoresService srv;

	public ActoresResource(ActoresService srv) {
		super();
		this.srv = srv;
	}


}
