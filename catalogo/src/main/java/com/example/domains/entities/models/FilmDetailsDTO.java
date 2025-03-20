package com.example.domains.entities.models;

import java.math.BigDecimal;
import java.util.List;

import com.example.domains.entities.Film;
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
                source.getActors().stream().map(item -> item.getFirstName() + " " + item.getLastName())
                    .sorted().toList(),
                source.getCategories().stream().map(item -> item.getName()).sorted().toList()
        );
    }
}
