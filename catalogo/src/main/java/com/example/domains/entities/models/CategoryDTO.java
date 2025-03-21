package com.example.domains.entities.models;

import com.example.domains.entities.Category;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
@Schema(name = "Categoría", description = "Datos de la categoría")
public class CategoryDTO {

	@JsonProperty("id")
	private int id;
	@Schema(name = "Nombre de la categoría", example = "Cartoon", minLength = 3, maxLength = 25)
	@JsonProperty("name")
	private String name;
	
	public static CategoryDTO from(Category source) {
		return new CategoryDTO(
				source.getCategoryId(),
				source.getName()
				);
	}
	
	public static Category from(CategoryDTO source) {
		return new Category(
				source.getId(),
				source.getName()
				);
	}
	

}
