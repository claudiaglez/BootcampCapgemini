package com.example.domains.contracts.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.domains.entities.Category;

@Repository
public interface CategoriesRepository extends JpaRepository<Category, Integer>, JpaSpecificationExecutor<Category> {
	
	List<Category> findByCategoryIdGreaterThan(int categoryId);
}
