package com.example.domains.entities.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.domains.contracts.services.FilmsService;
import com.example.domains.entities.Actor;
import com.example.domains.entities.Category;
import com.example.domains.entities.Film;
import com.example.domains.entities.FilmActor;
import com.example.domains.entities.FilmCategory;
import com.example.domains.entities.Language;
import com.example.exceptions.InvalidDataException;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name = "Pelicula (Editar)", description = "Version editable de las películas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmEditDTO {

	@Schema(description = "Identificador de la película", accessMode = AccessMode.READ_ONLY)
	private int filmId;

	@Schema(description = "Una breve descripción o resumen de la trama de la película", minLength = 2)
	private String description;

	@Schema(description = "Duración de la película en minutos")
	private Integer length;

	@Schema(description = "Clasificación de la película")
	@Pattern(regexp = "^(G|PG|PG-13|R|NC-17)$")
	private String rating;

	@Schema(description = "Año de estreno de la película")
	private Short releaseYear;

	@Schema(description = "Duración del alquiler en días")
	@NotNull
	private Byte rentalDuration;

	@Schema(description = "Coste del alquiler")
	@NotNull
	private BigDecimal rentalRate;

	@Schema(description = "Coste de la renovación del alquiler")
	@NotNull
	private BigDecimal replacementCost;

	@Schema(description = "Título de la película")
	@NotBlank
	@Size(min = 2, max = 128)
	private String title;

	@Schema(description = "El identificador del idioma de la película")
	@NotNull
	private Integer languageId;

	@Schema(description = "El identificador del idioma original de la película")
	private Integer languageVOId;

	@Schema(description = "Contenido Adicional")
	private List<String> specialFeatures = new ArrayList<>();

	@Schema(description = "La lista de identificadores de actores que participan en la película")
	private List<Integer> actors = new ArrayList<>();

	@Schema(description = "La lista de identificadores de categorías asignadas a la película")
	@ArraySchema(uniqueItems = true, minItems = 1, maxItems = 3)
	private List<Integer> categories = new ArrayList<>();

	public FilmEditDTO(int filmId, String description, Integer length, String rating, Short releaseYear,
			Byte rentalDuration, BigDecimal rentalRate, BigDecimal replacementCost, String title, Integer languageId,
			Integer languageVOId, List<Integer> actors, List<Integer> categories) {
		this.filmId = filmId;
		this.description = description;
		this.length = length;
		this.rating = rating;
		this.releaseYear = releaseYear;
		this.rentalDuration = rentalDuration;
		this.rentalRate = rentalRate;
		this.replacementCost = replacementCost;
		this.title = title;
		this.languageId = languageId;
		this.languageVOId = languageVOId;
		this.actors = actors;
		this.categories = categories;
	}

	public static FilmEditDTO from(Film source) {
	    return new FilmEditDTO(
	        source.getFilmId(),
	        source.getDescription(),
	        source.getLength(),
	        source.getRating(),
	        source.getReleaseYear(),
	        source.getRentalDuration(),
	        source.getRentalRate(),
	        source.getReplacementCost(),
	        source.getTitle(),
	        source.getLanguage() == null ? null : source.getLanguage().getLanguageId(),
	        source.getLanguageVO() == null ? null : source.getLanguageVO().getLanguageId(),
	        source.getActors().stream()
	            .map(actor -> actor.getActorId())
	            .collect(Collectors.toList()),
	        source.getCategories().stream()
	            .map(category -> category.getCategoryId())
	            .collect(Collectors.toList())
	    );
	}


	public static Film from(FilmEditDTO source) {
	    Film rslt = new Film();
	    rslt.setFilmId(source.getFilmId());
	    rslt.setTitle(source.getTitle());
	    rslt.setDescription(source.getDescription());
	    rslt.setReleaseYear(source.getReleaseYear());
	    Language language = new Language();  
	    language.setLanguageId(source.getLanguageId());  
	    rslt.setLanguage(language);  

	    if (source.getLanguageVOId() != null) {
	        Language languageVO = new Language();
	        languageVO.setLanguageId(source.getLanguageVOId());
	        rslt.setLanguageVO(languageVO);
	    }
	    rslt.setRentalDuration(source.getRentalDuration());
	    rslt.setRentalRate(source.getRentalRate());
	    rslt.setLength(source.getLength());
	    rslt.setReplacementCost(source.getReplacementCost());
	    rslt.setRating(source.getRating()); 
	    source.getActors().forEach(actorId -> {
	        FilmActor filmActor = new FilmActor();
	        filmActor.setActor(new Actor(actorId));  
	        filmActor.setFilm(rslt); 
	        rslt.addFilmActor(filmActor);  
	    });

	    source.getCategories().forEach(categoryId -> {
	        FilmCategory filmCategory = new FilmCategory();
	        filmCategory.setCategory(new Category(categoryId));  
	        filmCategory.setFilm(rslt);
	        rslt.addFilmCategory(filmCategory);
	    });
	    return rslt;
	}


}