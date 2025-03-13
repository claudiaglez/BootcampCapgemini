package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.domains.contracts.services.ActoresService;
import com.example.domains.entities.Actor;

@SpringBootApplication
@EntityScan(basePackages = "com.example.domains.entities")  
public class CatalogoApplication implements CommandLineRunner {

    @Autowired
    private ActoresService actoresService;

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Actores por prefijo 'Jo':");
        List<Actor> actoresPorPrefijo = actoresService.obtenerActoresPorPrefijo("Jo");
        actoresPorPrefijo.forEach(actor -> System.out.println(actor.getFirstName() + " " + actor.getLastName()));

        System.out.println("\nActores con ID mayor a 5:");
        List<Actor> actoresMayoresQue = actoresService.obtenerActoresMayoresQue(5);
        actoresMayoresQue.forEach(actor -> System.out.println(actor.getFirstName() + " " + actor.getLastName()));

        System.out.println("\nActores cuyo nombre empieza con la letra 'A':");
        List<Actor> actoresPorLetra = actoresService.obtenerActoresPorLetra("A");
        actoresPorLetra.forEach(actor -> System.out.println(actor.getFirstName() + " " + actor.getLastName()));
    }

    public static void main(String[] args) {
        SpringApplication.run(CatalogoApplication.class, args);
    }
}



