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

    @Autowired
    private ActoresService actoresService;
    
    @Autowired
    private LanguagesService languagesService;
    
    @Autowired
    private CategoriesService categoriesService;
    
    @Autowired
    private FilmsService filmsService;

    @Override
    public void run(String... args) throws Exception {
    	
        System.out.println("Lista de idiomas:");
        List<Language> idiomas = languagesService.obtenerIdiomas();  
        if (idiomas != null) {
            idiomas.forEach(language -> System.out.println(language.getName()));
        } else {
            System.out.println("La lista de idiomas está vacía o no fue encontrada.");
        }
        
        System.out.println("\nIdiomas que terminan con 'n':");
        List<Language> idiomasQueTerminanConN = languagesService.idiomasAcabanN("n");
        if (idiomasQueTerminanConN != null && !idiomasQueTerminanConN.isEmpty()) {
            idiomasQueTerminanConN.forEach(language -> System.out.println(language.getName()));
        } else {
            System.out.println("No se encontraron idiomas que terminen con 'n'.");
        }

    }

    public static void main(String[] args) {
        SpringApplication.run(CatalogoApplication.class, args);
    }
}



