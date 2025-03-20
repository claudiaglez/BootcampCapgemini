package com.example.domains.entities.models;

import java.util.ArrayList;

import com.example.domains.entities.Language;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(name = "Idioma", description = "Datos del idioma")
public class LanguageDTO {

    @JsonProperty("id")
    private int languageId;
    @Schema(name = "Nombre del idioma", example = "English", minLength = 3, maxLength = 20)
    @JsonProperty("name")
    private String name;

    public static LanguageDTO from(Language source) {
        return new LanguageDTO(
                source.getLanguageId(),
                source.getName()
        );
    }

    public static Language from(LanguageDTO source) {
        Language language = new Language(); 
        language.setLanguageId(source.getLanguageId());
        language.setName(source.getName());
        language.setLastUpdate(null);  
        language.setFilms(new ArrayList<>());  
        language.setFilmsVO(new ArrayList<>()); 
        return language;
    }


}
