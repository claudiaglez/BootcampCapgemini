package com.example.domains.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.domains.contracts.repositories.FilmsRepository;
import com.example.domains.contracts.services.FilmsService;
import com.example.domains.entities.Film;
import com.example.domains.entities.models.FilmDetailsDTO;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

import jakarta.validation.Valid;

@Service
public class FilmsServiceImpl implements FilmsService {
	@Autowired
	private FilmsRepository filmsRepository;
	
	public FilmsServiceImpl(FilmsRepository filmsRepository) {
		this.filmsRepository = filmsRepository;
	}


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
        
        if(item.isInvalid())
			throw new InvalidDataException(item.getErrorsMessage(), item.getErrorsFields());
		var read = filmsRepository.findById(item.getFilmId()).orElseThrow(() -> new NotFoundException());
		return filmsRepository.save(item.merge(read));
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
