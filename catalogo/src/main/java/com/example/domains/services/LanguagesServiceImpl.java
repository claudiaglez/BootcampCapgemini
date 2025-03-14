package com.example.domains.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domains.contracts.repositories.LanguagesRepository;
import com.example.domains.contracts.services.LanguagesService;
import com.example.domains.entities.Language;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

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
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Language add(Language item) throws DuplicateKeyException, InvalidDataException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Language modify(Language item) throws NotFoundException, InvalidDataException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Language item) throws InvalidDataException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
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
