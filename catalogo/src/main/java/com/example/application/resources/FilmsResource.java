package com.example.application.resources;

import java.net.URI;
import java.util.List;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
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

import com.example.domains.contracts.services.FilmsService;
import com.example.domains.entities.Film;
import com.example.domains.entities.models.FilmDetailsDTO;
import com.example.domains.entities.models.FilmEditDTO;
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
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/peliculas/v1")
@Tag(name = "peliculas-service", description = "Gestión de películas")
public class FilmsResource {
	@Autowired
	private FilmsService filmsService;

	public FilmsResource(FilmsService filmsService) {
		super();
		this.filmsService = filmsService;
	}

	@GetMapping
	@Hidden
	public List<FilmDetailsDTO> getAll() {
		return filmsService.getByProjection(FilmDetailsDTO.class);
	}

	@GetMapping(params = { "page" })
	@Operation(summary= "Obtiene las películas paginadas")
	public Page<FilmDetailsDTO> getAll(@ParameterObject Pageable pageable){
		return filmsService.getByProjection(pageable, FilmDetailsDTO.class);
	}

	@GetMapping(path = "/{id}")
	@Operation(summary = "Obtiene una película por su id")
	public FilmDetailsDTO getOne(@PathVariable @Parameter(description = "Identificador de la película") int id)
			throws NotFoundException {
		var item = filmsService.getOne(id);
		if (item.isEmpty()) {
			throw new NotFoundException("No se encontró la película con id " + id);
		}
		return FilmDetailsDTO.from(item.get());
	}

	@PostMapping
	@Operation(summary = "Crea una película")
	@ApiResponse(responseCode = "201", description = "Película creada")
	public ResponseEntity<Object> create(@Valid @RequestBody FilmEditDTO item)
			throws BadRequestException, DuplicateKeyException, InvalidDataException {
		Film newFilm = filmsService.add(FilmEditDTO.from(item));
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newFilm.getFilmId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping("/{id}")
	@Operation(summary = "Modifica una película por su id")
	@ApiResponse(responseCode = "204", description = "Película a modificar")
	@ApiResponse(responseCode = "400", description = "El id de la película no coincide con el recurso a modificar")
	@ApiResponse(responseCode = "404", description = "Película no encontrada")
	@ApiResponse(responseCode = "422", description = "Datos inválidos proporcionados en el cuerpo de la solicitud")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable int id, @Valid @RequestBody FilmEditDTO item) throws BadRequestException, NotFoundException, InvalidDataException {
	    if (item.getFilmId() != id) {
	        throw new BadRequestException("El id de la película no coincide con el recurso a modificar");
	    }
	    filmsService.modify(FilmEditDTO.from(item));
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Borra un actor por su id")
	@ApiResponse(responseCode = "204", description = "Película a borrar")
	@ApiResponse(responseCode = "404", description = "Película no encontrada")
	@ApiResponse(responseCode = "400", description = "ID inválido proporcionado")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) {
		filmsService.deleteById(id);
	}

}
