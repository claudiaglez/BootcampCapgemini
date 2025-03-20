package com.example.domains.entities.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.example.domains.entities.Actor;
import com.example.domains.entities.Category;
import com.example.domains.entities.Film;
import com.example.domains.entities.FilmActor;
import com.example.domains.entities.FilmCategory;
import com.example.domains.entities.Language;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.Value;

@Schema(name = "Detalles Película", description = "Versión detallada de las películas")
@Value
public class FilmDetailsDTO {
    @Schema(description = "Identificador de la pelicula", accessMode = AccessMode.READ_ONLY)
    private int filmId;

    @Schema(description = "Descripción del argumento de la película")
    private String description;

    @Schema(description = "Duración de la película en minutos")
    private Integer length;

    @Schema(description = "Clasificación de la película")
    private String rating; 
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy")
    @Schema(description = "Año de estreno de la película")
    private Short releaseYear;

    @Schema(description = "Duración del alquiler en días")
    private Byte rentalDuration;

    @Schema(description = "Coste del alquiler")
    private BigDecimal rentalRate;

    @Schema(description = "Coste de la renovación del alquiler")
    private BigDecimal replacementCost;

    @Schema(description = "Título de la película")
    private String title;

    @Schema(description = "Idioma de la película")
    private String language;

    @Schema(description = "Idioma en versión original de la película")
    private String languageVO;

    @Schema(description = "Lista de actores de la película")
    private List<String> actors;

    @Schema(description = "Categorías asignadas a la película")
    private List<String> categories;

    public static FilmDetailsDTO from(Film source) {
        return new FilmDetailsDTO(
                source.getFilmId(),
                source.getDescription(),
                source.getLength(),
                source.getRating(),
                source.getReleaseYear(),
                source.getRentalDuration(),
                source.getRentalRate(),
                source.getReplacementCost(),
                source.getTitle(),
                source.getLanguage() == null ? null : source.getLanguage().getName(),
                source.getLanguageVO() == null ? null : source.getLanguageVO().getName(),
                source.getActors().stream().map(actor -> actor.getFirstName() + " " + actor.getLastName())
                        .sorted().toList(),
                source.getCategories().stream().map(category -> category.getName())
                        .sorted().toList()
        );
    }

    
    public static Film toEntity(FilmDetailsDTO dto) {
        Film film = new Film();
        film.setFilmId(dto.getFilmId());  
        film.setDescription(dto.getDescription());
        film.setLength(dto.getLength());
        film.setRating(dto.getRating());
        film.setReleaseYear(dto.getReleaseYear());
        film.setRentalDuration(dto.getRentalDuration());
        film.setRentalRate(dto.getRentalRate());
        film.setReplacementCost(dto.getReplacementCost());
        film.setTitle(dto.getTitle());

        if (dto.getLanguage() != null) {
            Language language = new Language();
            language.setName(dto.getLanguage()); 
            film.setLanguage(language);
        }

        if (dto.getLanguageVO() != null) {
            Language languageVO = new Language();
            languageVO.setName(dto.getLanguageVO()); 
            film.setLanguageVO(languageVO);
        }

        if (dto.getActors() != null && !dto.getActors().isEmpty()) {
            List<FilmActor> filmActors = new ArrayList<>();
            for (String actorName : dto.getActors()) {
                Actor actor = new Actor(); 
                actor.setFirstName(actorName);  
                FilmActor filmActor = new FilmActor();
                filmActor.setActor(actor);
                filmActors.add(filmActor);
            }
            film.setFilmActors(filmActors); 
        }

        if (dto.getCategories() != null && !dto.getCategories().isEmpty()) {
            List<FilmCategory> filmCategories = new ArrayList<>();
            for (String categoryName : dto.getCategories()) {
                Category category = new Category();
                category.setName(categoryName); 
                FilmCategory filmCategory = new FilmCategory();
                filmCategory.setCategory(category);
                filmCategories.add(filmCategory);
            }
            film.setFilmCategories(filmCategories);
        }

        return film;
    }
}
