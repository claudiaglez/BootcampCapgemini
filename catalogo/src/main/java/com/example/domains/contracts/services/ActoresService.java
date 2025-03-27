package com.example.domains.contracts.services;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;

import com.example.domains.core.contracts.services.DomainService;
import com.example.domains.entities.Actor;
import com.example.domains.entities.models.ActorDTO;

public interface ActoresService extends DomainService<Actor, Integer> {

	List<ActorDTO> getByProjection(Class<ActorDTO> class1);
	Page<ActorDTO> getByProjection(Pageable pageable, Class<ActorDTO> class1);

    
}