package com.example.domains.contracts.services;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;

import com.example.domains.core.contracts.services.DomainService;
import com.example.domains.entities.Category;
import com.example.domains.entities.models.ActorDTO;
import com.example.domains.entities.models.CategoryDTO;

public interface CategoriesService extends DomainService<Category, Integer> {
	
	List<Category> obtenerCategoriasMayoresQue(int id);

	List<CategoryDTO> getByProjection(Class<CategoryDTO> class1);

	Page<CategoryDTO> getByProjection(Pageable pageable, Class<CategoryDTO> class1);

}
