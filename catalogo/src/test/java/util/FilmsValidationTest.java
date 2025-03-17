package util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.CatalogoApplication;
import com.example.domains.entities.Category;
import com.example.domains.entities.Film;
import com.example.domains.entities.Language;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

@SpringBootTest(classes = CatalogoApplication.class)
public class FilmsValidationTest {

    @Autowired
    private Validator validator; 

    @Test
    public void testTitleNotNull() {
        Film film = new Film();
        film.setTitle("Cartoon in the snow");  

        film.setRating("PG");
        film.setReleaseYear((short) 2025); 
        film.setRentalDuration((byte) 5); 
        film.setRentalRate(new BigDecimal("1.99")); 
        film.setReplacementCost(new BigDecimal("19.99")); 

        Language englishLanguage = new Language();
        englishLanguage.setLanguageId(1);  
        englishLanguage.setName("English");  

        film.setLanguage(englishLanguage);
        film.setFilmId(1);

        Set<ConstraintViolation<Film>> violations = validator.validate(film);

        assertTrue(violations.isEmpty(), "Violations: " + violations);
    }
    
    @Test
    public void testTitleNull() {
        Film film = new Film();
        film.setTitle(null); 

        film.setRating("PG");
        film.setReleaseYear((short) 2025); 
        film.setRentalDuration((byte) 5); 
        film.setRentalRate(new BigDecimal("1.99")); 
        film.setReplacementCost(new BigDecimal("19.99")); 

        Language englishLanguage = new Language();
        englishLanguage.setLanguageId(1);  
        englishLanguage.setName("English");  

        film.setLanguage(englishLanguage);
        film.setFilmId(1);

        Set<ConstraintViolation<Film>> violations = validator.validate(film);

        assertFalse(violations.isEmpty()); 
        boolean foundNullMessage = violations.stream()
                                             .anyMatch(v -> "El título de la película no puede estar vacío".equals(v.getMessage()));
        assertTrue(foundNullMessage);  
    }
    
    @Test
    public void testEmptyTitle() {
        Film film = new Film();
        film.setTitle(""); 
        film.setRating("PG");
        film.setReleaseYear((short) 2025); 
        film.setRentalDuration((byte) 5); 
        film.setRentalRate(new BigDecimal("1.99")); 
        film.setReplacementCost(new BigDecimal("19.99")); 

        Language englishLanguage = new Language();
        englishLanguage.setLanguageId(1);  
        englishLanguage.setName("English");  

        film.setLanguage(englishLanguage);
        film.setFilmId(1);

        Set<ConstraintViolation<Film>> violations = validator.validate(film);

        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size()); 

        boolean foundNotEmptyViolation = false;

        for (ConstraintViolation<Film> violation : violations) {
            if ("El título de la película no puede estar vacío".equals(violation.getMessage())) {
                foundNotEmptyViolation = true;
            }
        }

        assertTrue(foundNotEmptyViolation);
    }

}
