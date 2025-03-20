package com.example.application.resources;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domains.contracts.services.LanguagesService;
import com.example.domains.entities.models.CategoryDTO;
import com.example.domains.entities.models.LanguageDTO;
import com.example.exceptions.NotFoundException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
	
	@GetMapping(path = "/{id}")
	@Operation(summary = "Obtiene un idioma por su id")
	public LanguageDTO getOne(@PathVariable @Parameter(description = "Identificador del idioma") int id) throws NotFoundException {
		var item = languagesService.getOne(id);
		if (item.isEmpty()) {
			throw new NotFoundException("No se encontró el idioma con id " + id);
		}
		return LanguageDTO.from(item.get());
	}
	

}
