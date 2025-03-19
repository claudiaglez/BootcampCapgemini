package com.example.domains.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domains.contracts.repositories.ActoresRepository;
import com.example.domains.contracts.services.ActoresService;
import com.example.domains.entities.Actor;
import com.example.domains.entities.models.ActorDTO;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

import jakarta.validation.Valid;

@Service
public class ActoresServiceImpl implements ActoresService {
    @Autowired
    private ActoresRepository actoresRepository;  

    @Override
    public List<Actor> getAll() {
        return actoresRepository.findAll();
    }

    @Override
    public Optional<Actor> getOne(Integer id) {
        return actoresRepository.findById(id);
    }

    @Override
    public Actor add(@Valid Actor item) throws DuplicateKeyException, InvalidDataException {
        if (item == null) {
            throw new InvalidDataException("El actor no puede ser nulo");
        }
        if (actoresRepository.existsById(item.getActorId())) {
            throw new DuplicateKeyException("El actor con este ID ya existe");
        }
        return actoresRepository.save(item);
    }

    @Override
    public Actor modify(@Valid Actor item) throws NotFoundException, InvalidDataException {
        if (item == null) {
            throw new InvalidDataException("El actor no puede ser nulo");
        }
        if (!actoresRepository.findById(item.getActorId()).isPresent()) {
            throw new NotFoundException("Actor no encontrado");
        }
        return actoresRepository.save(item);
    }

    @Override
    public void delete(Actor item) throws InvalidDataException {
        if (item == null) {
            throw new InvalidDataException("El actor no existe");
        }
        actoresRepository.delete(item);
    }

    @Override
    public void deleteById(Integer id) {
        actoresRepository.deleteById(id);
    }

    @Override
    public List<Actor> obtenerActoresPorPrefijo(String prefijo) {
        return actoresRepository.findByFirstNameStartingWith(prefijo);
    }

    @Override
    public List<Actor> obtenerActoresMayoresQue(int id) {
        return actoresRepository.findByActorIdGreaterThan(id);
    }

    @Override
    public List<Actor> obtenerActoresPorLetra(String letra) {
        return actoresRepository.findByFirstNameStartingWith(letra);
    }

    @Override
    public List<ActorDTO> getByProjection(Class<ActorDTO> class1) {
        List<Actor> actores = actoresRepository.findAll();
        return actores.stream()
                      .map(actor -> ActorDTO.from(actor)) 
                      .collect(Collectors.toList());
    }

}
