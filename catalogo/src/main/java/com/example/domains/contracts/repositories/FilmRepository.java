package com.example.domains.contracts.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.domains.entities.Film;

@Repository
public interface FilmRepository extends JpaRepository<Film, Integer>, JpaSpecificationExecutor<Film> {
	List<Film> findByReleaseYear(Short releaseYear);

}
