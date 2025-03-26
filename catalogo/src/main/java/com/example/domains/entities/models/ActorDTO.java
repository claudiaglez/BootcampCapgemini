package com.example.domains.entities.models;


import com.example.domains.entities.Actor;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
@Schema(name = "Actor", description = "Datos del actor")
public class ActorDTO {

	@JsonProperty("id")
	private int actorId;
	@Schema(name = "name", example = "Fiona", minLength = 2, maxLength = 45)
	@JsonProperty("name")
	private String firstName;
	@Schema(name = "surname", example = "Apple", minLength = 2, maxLength = 45)
	@JsonProperty("surname")
	private String lastName;
	
	public static ActorDTO from(Actor source) {
		return new ActorDTO(
				source.getActorId(),
				source.getFirstName(),
				source.getLastName()
				);
				
	}
	
	public static Actor from(ActorDTO source) {
		return new Actor(
				source.getActorId(),
				source.getFirstName(),
				source.getLastName()				
				);
				
	}

}
