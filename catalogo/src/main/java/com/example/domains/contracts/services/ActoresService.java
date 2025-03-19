package com.example.domains.contracts.services;

import java.util.List;

import com.example.domains.core.contracts.services.DomainService;
import com.example.domains.entities.Actor;
import com.example.domains.entities.models.ActorDTO;

public interface ActoresService extends DomainService<Actor, Integer> {

    List<Actor> obtenerActoresPorPrefijo(String prefijo);
    List<Actor> obtenerActoresMayoresQue(int id);
    List<Actor> obtenerActoresPorLetra(String letra);
	List<ActorDTO> getByProjection(Class<ActorDTO> class1);

    
}