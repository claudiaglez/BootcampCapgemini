package com.example.domains.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domains.contracts.repositories.LanguagesRepository;
import com.example.domains.contracts.services.LanguagesService;
import com.example.domains.entities.Category;
import com.example.domains.entities.Language;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

import jakarta.validation.Valid;

@Service
public class LanguagesServiceImpl implements LanguagesService {
	
	@Autowired
	private LanguagesRepository languagesRepository;

	public LanguagesServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Language> getAll() {
		 return languagesRepository.findAll();
	}

	@Override
	public Optional<Language> getOne(Integer id) {
		return languagesRepository.findById(id);
	}

	 @Override
	    public Language add(@Valid Language item) throws DuplicateKeyException, InvalidDataException {
	        if (item == null) {
	            throw new InvalidDataException("El idioma no puede ser nulo");
	        }
	        if (languagesRepository.existsById(item.getLanguageId())) {
	            throw new DuplicateKeyException("El idioma con este ID ya existe");
	        }
	        return languagesRepository.save(item);
	    }

	 @Override
	    public Language modify(@Valid Language item) throws NotFoundException, InvalidDataException {
	        if (item == null) {
	            throw new InvalidDataException("El idioma no puede ser nulo");
	        }
	        if (!languagesRepository.findById(item.getLanguageId()).isPresent()) {
	            throw new NotFoundException("Idioma no encontrado");
	        }
	        return languagesRepository.save(item);
	    }

	 @Override
	    public void delete(Language item) throws InvalidDataException {
	        if (item == null) {
	            throw new InvalidDataException("El idioma no puede ser nulo");
	        }
	        languagesRepository.delete(item);
	    }

	@Override
	public void deleteById(Integer id) {
		languagesRepository.deleteById(id);
		
	}

	@Override
	public List<Language> obtenerIdiomas() {
		return languagesRepository.findAll();
	}
	
	   @Override
	    public List<Language> idiomasAcabanN(String letra) {
	        return languagesRepository.findByNameEndingWith(letra);
	    }

}
