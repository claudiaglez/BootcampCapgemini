package com.example.domains.contracts.services;

import java.util.List;

import com.example.domains.core.contracts.services.DomainService;
import com.example.domains.entities.Film;

public interface FilmService extends DomainService<Film, Integer> {
	
	List<Film> obtenerPeliculasPorFechaLanzamiento(Short fecha);

}
