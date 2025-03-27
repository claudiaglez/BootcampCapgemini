package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.domains.contracts.services.ActoresService;
import com.example.domains.contracts.services.CategoriesService;
import com.example.domains.contracts.services.FilmsService;
import com.example.domains.contracts.services.LanguagesService;
import com.example.domains.entities.Actor;
import com.example.domains.entities.Category;
import com.example.domains.entities.Film;
import com.example.domains.entities.Language;

@SpringBootApplication
@EntityScan(basePackages = "com.example.domains.entities")  
public class CatalogoApplication implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {

    }

    public static void main(String[] args) {
        SpringApplication.run(CatalogoApplication.class, args);
    }
}



