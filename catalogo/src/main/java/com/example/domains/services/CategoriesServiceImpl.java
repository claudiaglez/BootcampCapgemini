package com.example.domains.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.domains.contracts.repositories.ActoresRepository;
import com.example.domains.contracts.repositories.CategoriesRepository;
import com.example.domains.contracts.services.CategoriesService;
import com.example.domains.entities.Actor;
import com.example.domains.entities.Category;
import com.example.domains.entities.models.CategoryDTO;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

import jakarta.validation.Valid;

@Service
public class CategoriesServiceImpl implements CategoriesService {
	
	@Autowired
    private CategoriesRepository categoriesRepository;  


	public CategoriesServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Category> getAll() {
		 return categoriesRepository.findAll();
	}

	@Override
	public Optional<Category> getOne(Integer id) {
		return categoriesRepository.findById(id);
	}

    @Override
    public Category add(@Valid Category item) throws DuplicateKeyException, InvalidDataException {
        if (item == null) {
            throw new InvalidDataException("La categoría no puede ser nula");
        }
        if (categoriesRepository.existsById(item.getCategoryId())) {
            throw new DuplicateKeyException("La categoría con este ID ya existe");
        }
        return categoriesRepository.save(item);
    }

    @Override
    public Category modify(@Valid Category item) throws NotFoundException, InvalidDataException {
        if (item == null) {
            throw new InvalidDataException("La categoría no puede ser nula");
        }
        if (!categoriesRepository.findById(item.getCategoryId()).isPresent()) {
            throw new NotFoundException("Categoría no encontrada");
        }
        return categoriesRepository.save(item);
    }

    @Override
    public void delete(Category item) throws InvalidDataException {
        if (item == null) {
            throw new InvalidDataException("La categoría no puede ser nula");
        }
        categoriesRepository.delete(item);
    }

	@Override
	public void deleteById(Integer id) {
		categoriesRepository.deleteById(id);
		
	}
	
	 @Override
	    public List<Category> obtenerCategoriasMayoresQue(int id) {
	        return categoriesRepository.findByCategoryIdGreaterThan(id);
	    }

	@Override
	public List<CategoryDTO> getByProjection(Class<CategoryDTO> class1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<CategoryDTO> getByProjection(Pageable pageable, Class<CategoryDTO> class1) {
		// TODO Auto-generated method stub
		return null;
	}

}
