package com.example.domains.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.domains.contracts.repositories.FilmsRepository;
import com.example.domains.contracts.services.FilmsService;
import com.example.domains.entities.Actor;
import com.example.domains.entities.Film;
import com.example.domains.entities.FilmActor;
import com.example.domains.entities.models.FilmDetailsDTO;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

import jakarta.validation.Valid;

@Service
public class FilmsServiceImpl implements FilmsService {
	@Autowired
	private FilmsRepository filmsRepository;


	@Override
	public List<Film> getAll() {
		return filmsRepository.findAll();
	}

	@Override
	public Optional<Film> getOne(Integer id) {
		return filmsRepository.findById(id);
	}

    @Override
    public Film add(@Valid Film item) throws DuplicateKeyException, InvalidDataException {
        if (item == null) {
            throw new InvalidDataException("La película no puede ser nula");
        }
        if (filmsRepository.existsById(item.getFilmId())) {
            throw new DuplicateKeyException("La película con este ID ya existe");
        }
        return filmsRepository.save(item);
    }

    @Override
    public Film modify(@Valid Film item) throws NotFoundException, InvalidDataException {
        if (item == null) {
            throw new InvalidDataException("La película no puede ser nula");
        }
        
        Film existingFilm = filmsRepository.findById(item.getFilmId())
                .orElseThrow(() -> new NotFoundException("Película no encontrada"));

        existingFilm.setTitle(item.getTitle());
        existingFilm.setDescription(item.getDescription());
        existingFilm.setReleaseYear(item.getReleaseYear());
     
        updateFilmActors(existingFilm, item.getActors());

        return filmsRepository.save(existingFilm);
    }

    private void updateFilmActors(Film film, List<Actor> actors) {
        Set<FilmActor> currentFilmActors = new HashSet<>(film.getFilmActors());

        for (FilmActor filmActor : currentFilmActors) {
            if (!actors.contains(filmActor.getActor())) {
                film.removeActor(filmActor.getActor());
            }
        }

        for (Actor actor : actors) {
            if (film.getActors().stream().noneMatch(existingActor -> existingActor.getActorId() == actor.getActorId())) {
                film.addActor(actor);
            }
        }
    }




    @Override
    public void delete(@Valid Film item) throws InvalidDataException {
        if (item == null) {
            throw new InvalidDataException("La película no puede ser nula");
        }
        filmsRepository.delete(item);
    }

	@Override
	public void deleteById(Integer id) {
		filmsRepository.deleteById(id);
		
	}

	@Override
	public List<Film> obtenerPeliculasPorFechaLanzamiento(Short fecha) {
		return filmsRepository.findByReleaseYear(fecha);
	}

	@Override
	public List<Film> obtenerPeliculasPorTitulo(String titulo) {
		return filmsRepository.findByTitleContainingIgnoreCase(titulo);
	}

	@Override
	public List<FilmDetailsDTO> getByProjection(Class<FilmDetailsDTO> class1) {
		 List<Film> films = filmsRepository.findAll();
	        return films.stream()
	                      .map(film -> FilmDetailsDTO.from(film)) 
	                      .collect(Collectors.toList());
	}
	

	@Override
	public Page<FilmDetailsDTO> getByProjection(Pageable pageable, Class<FilmDetailsDTO> class1) {
		// TODO Auto-generated method stub
		return null;
	}




}
