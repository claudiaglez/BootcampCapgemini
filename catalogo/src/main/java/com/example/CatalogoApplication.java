package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.domains.contracts.services.ActoresService;
import com.example.domains.contracts.services.CategoriesService;
import com.example.domains.contracts.services.LanguagesService;
import com.example.domains.entities.Actor;
import com.example.domains.entities.Category;
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
        
        System.out.println("\nCategorías con ID mayor a 3:");
        List<Category> categoriasMayoresQue = categoriesService.obtenerCategoriasMayoresQue(3);
        categoriasMayoresQue.forEach(category -> System.out.println(category.getName()));
    }

    public static void main(String[] args) {
        SpringApplication.run(CatalogoApplication.class, args);
    }
}



