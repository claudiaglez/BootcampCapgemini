package com.example.application.resources;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domains.contracts.services.LanguagesService;
import com.example.domains.entities.models.LanguageDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/idiomas/v1")
@Tag(name = "idiomas-service", description = "Gestión de idiomas")
public class LanguagesResource {
	private LanguagesService languagesService;
	
	public LanguagesResource(LanguagesService languagesService) {
		super();
		this.languagesService = languagesService;
	}
	
	@GetMapping
	@Operation(summary = "Obtiene todos los idiomas")
	public List<LanguageDTO> getAll() {
		return languagesService.getByProjection(LanguageDTO.class);
	}

}
