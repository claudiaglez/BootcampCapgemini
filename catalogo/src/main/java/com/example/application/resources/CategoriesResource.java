package com.example.application.resources;

import java.net.URI;
import java.util.List;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.domains.contracts.services.CategoriesService;
import com.example.domains.entities.models.CategoryDTO;
import com.example.exceptions.BadRequestException;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/categorias/v1")
@Tag(name = "categorias-service", description = "Gestión de categorías")
public class CategoriesResource {
	private CategoriesService categoriesService;
	
	public CategoriesResource(CategoriesService categoriesService) {
		super();
		this.categoriesService = categoriesService;
	}
	
	@GetMapping
	@Operation(summary = "Obtiene todas las categorías")
	public List<CategoryDTO> getAll() {
		return categoriesService.getByProjection(CategoryDTO.class);
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
	
	@PostMapping
	@Operation(summary = "Crea una categoría")
	@ApiResponse(responseCode = "201", description = "Categoría creada")
	public ResponseEntity<Object> create(@Valid @RequestBody CategoryDTO item) throws BadRequestException, DuplicateKeyException, InvalidDataException {
		var newItem = categoriesService.add(CategoryDTO.from(item));
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
			.buildAndExpand(newItem.getCategoryId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/{id}")
	@Operation(summary = "Modifica una categoría por su id")
	@ApiResponse(responseCode = "204", description = "Categoría a modificar")
	@ApiResponse(responseCode = "400", description = "El id de la categoría no coincide con el recurso a modificar")
	@ApiResponse(responseCode = "404", description = "Categoría no encontrada")
	@ApiResponse(responseCode = "422", description = "Datos inválidos proporcionados en el cuerpo de la solicitud")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable int id, @Valid @RequestBody CategoryDTO item) throws BadRequestException, NotFoundException, InvalidDataException {
		if (item.getId() != id) {
			throw new BadRequestException("El id de la categoría no coincide con el recurso a modificar");
		}
		categoriesService.modify(CategoryDTO.from(item));
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Borra una categoría por su id")
	@ApiResponse(responseCode = "204", description = "Categoría a borrar")
	@ApiResponse(responseCode = "404", description = "Categoría no encontrada")
	@ApiResponse(responseCode = "400", description = "ID inválido proporcionado")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) {
		categoriesService.deleteById(id);
	}
	

}
