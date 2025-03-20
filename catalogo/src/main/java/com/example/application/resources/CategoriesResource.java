package com.example.application.resources;

import java.util.List;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domains.contracts.services.CategoriesService;
import com.example.domains.entities.models.CategoryDTO;
import com.example.exceptions.NotFoundException;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/categorías/v1")
@Tag(name = "categorias-service", description = "Gestión de categorías")
public class CategoriesResource {
	private CategoriesService categoriesService;
	
	public CategoriesResource(CategoriesService categoriesService) {
		super();
		this.categoriesService = categoriesService;
	}
	
	@GetMapping
	@Hidden
	public List<CategoryDTO> getAll() {
		return categoriesService.getByProjection(CategoryDTO.class);
	}
	
	@GetMapping(params = { "page" })
	@Operation(summary = "Obtiene todas las categorías paginadas")
	public Page<CategoryDTO> getAll(@ParameterObject Pageable pageable){
		return categoriesService.getByProjection(pageable, CategoryDTO.class);
	}
	
	@GetMapping(path = "/{id}")
	@Operation(summary = "Obtiene una categoría por su id")
	public CategoryDTO getOne(@PathVariable @Parameter(description = "Identificador de la categoría") int id) throws NotFoundException {
		var item = categoriesService.getOne(id);
		if (item.isEmpty()) {
			throw new NotFoundException("No se encontró la categoría con id " + id);
		}
		return CategoryDTO.from(item.get());
	}
	
	

}
