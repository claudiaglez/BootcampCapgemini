package com.example.domains.contracts.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.domains.entities.Language;
import com.example.domains.entities.models.LanguageDTO;

@Repository
public interface LanguagesRepository extends JpaRepository<Language, Integer>{
	 List<Language> findByNameEndingWith(String ending);
}
