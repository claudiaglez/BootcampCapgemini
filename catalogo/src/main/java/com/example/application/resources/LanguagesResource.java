package com.example.application.resources;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import com.example.domains.contracts.services.LanguagesService;
import com.example.domains.entities.Language;
import com.example.domains.entities.models.CategoryDTO;
import com.example.domains.entities.models.LanguageDTO;
import com.example.exceptions.BadRequestException;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
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
	
	@PostMapping
	@Operation(summary = "Crea un idioma")
	@ApiResponse(responseCode = "201", description = "Idioma creado")
	public ResponseEntity<Object> create(@Valid @RequestBody LanguageDTO item) throws BadRequestException, DuplicateKeyException, InvalidDataException {
		var newItem = languagesService.add(LanguageDTO.from(item));
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
			.buildAndExpand(newItem.getLanguageId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/{id}")
	@Operation(summary = "Modifica un idioma por su id")
	@ApiResponse(responseCode = "204", description = "Idioma a modificar")
	@ApiResponse(responseCode = "400", description = "El id del idioma no coincide con el recurso a modificar")
	@ApiResponse(responseCode = "404", description = "Idioma no encontrado")
	@ApiResponse(responseCode = "422", description = "Datos inválidos proporcionados en el cuerpo de la solicitud")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable int id, @Valid @RequestBody LanguageDTO item) throws BadRequestException, NotFoundException, InvalidDataException {
		if (item.getLanguageId() != id) {
			throw new BadRequestException("El id de la categoría no coincide con el recurso a modificar");
		}
		languagesService.modify(LanguageDTO.from(item));
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Borra una idioma por su id")
	@ApiResponse(responseCode = "204", description = "Idioma a borrar")
	@ApiResponse(responseCode = "404", description = "Idioma no encontrado")
	@ApiResponse(responseCode = "400", description = "ID inválido proporcionado")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) {
		languagesService.deleteById(id);
	}
	

}
