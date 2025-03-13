package com.example.domains.contracts.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.domains.entities.Actor;

@Repository
public interface ActoresRepository extends JpaRepository<Actor, Integer>, JpaSpecificationExecutor<Actor> {

    List<Actor> findTop3ByFirstNameStartingWith(String prefijo, Sort sort);

    List<Actor> findByActorIdGreaterThan(int actorId);

    List<Actor> findByFirstNameStartingWith(String letra);

}
