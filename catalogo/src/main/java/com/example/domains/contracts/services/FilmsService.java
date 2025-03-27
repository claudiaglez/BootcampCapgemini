package com.example.domains.contracts.services;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;

import com.example.domains.core.contracts.services.DomainService;
import com.example.domains.entities.Film;
import com.example.domains.entities.models.FilmDetailsDTO;

public interface FilmsService extends DomainService<Film, Integer> {

	Page<FilmDetailsDTO> getByProjection(Pageable pageable, Class<FilmDetailsDTO> class1);
	List<FilmDetailsDTO> getByProjection(Class<FilmDetailsDTO> class1);
	
	

}
