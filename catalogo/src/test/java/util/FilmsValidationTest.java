package util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.domains.entities.Film;
import com.example.domains.entities.Language;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class FilmsValidationTest {

private Validator validator;
	
	@BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
	
	@Test
	public void testNameNotNull() {
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
	public void testNameNull() {
	    Film film = new Film();
	    film.setTitle(" ");  

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

	
}
