package com.example.domains.contracts.services;

import java.util.List;

import com.example.domains.core.contracts.services.DomainService;
import com.example.domains.entities.Category;

public interface CategoriesService extends DomainService<Category, Integer> {
	
	List<Category> obtenerCategoriasMayoresQue(int id);

}
