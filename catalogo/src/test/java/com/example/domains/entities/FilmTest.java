//package com.example.domains.entities;
//
//import static org.assertj.core.api.Assertions.fail;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import java.math.BigDecimal;
//import java.util.Set;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.example.CatalogoApplication;
//import com.example.domains.entities.Film;
//import com.example.domains.entities.Language;
//
//import jakarta.validation.ConstraintViolation;
//import jakarta.validation.Validator;
//
//@SpringBootTest(classes = CatalogoApplication.class)
//public class FilmTest {
//
//    @Autowired
//    private Validator validator; 
//
//    @Test
//    @DisplayName("Título de la película no nulo")
//    public void testTitleNotNull() {
//        Film film = new Film();
//        film.setTitle("Cartoon in the snow");  
//
////        film.setRating("PG");
//        film.setReleaseYear((short) 2025); 
//        film.setRentalDuration((byte) 5); 
//        film.setRentalRate(new BigDecimal("1.99")); 
//        film.setReplacementCost(new BigDecimal("19.99")); 
//
//        Language englishLanguage = new Language();
//        englishLanguage.setLanguageId(1);  
//        englishLanguage.setName("English");  
//
//        film.setLanguage(englishLanguage);
//        film.setFilmId(1);
//
//        Set<ConstraintViolation<Film>> violations = validator.validate(film);
//
//        assertTrue(violations.isEmpty(), "Violations: " + violations);
//    }
//    
//    @Test
//    @DisplayName("Título de la película nulo")
//    public void testTitleNull() {
//        Film film = new Film();
//        film.setTitle(null); 
//
//        film.setRating("PG");
//        film.setReleaseYear((short) 2025); 
//        film.setRentalDuration((byte) 5); 
//        film.setRentalRate(new BigDecimal("1.99")); 
//        film.setReplacementCost(new BigDecimal("19.99")); 
//
//        Language englishLanguage = new Language();
//        englishLanguage.setLanguageId(1);  
//        englishLanguage.setName("English");  
//
//        film.setLanguage(englishLanguage);
//        film.setFilmId(1);
//
//        Set<ConstraintViolation<Film>> violations = validator.validate(film);
//
//        assertFalse(violations.isEmpty()); 
//        boolean foundNullMessage = violations.stream()
//                                             .anyMatch(v -> "El título de la película no puede estar vacío".equals(v.getMessage()));
//        assertTrue(foundNullMessage);  
//    }
//    
//    @Test
//    @DisplayName("Título de la película vacío")
//    public void testEmptyTitle() {
//        Film film = new Film();
//        film.setTitle(""); 
////        film.setRating("PG");
//        film.setReleaseYear((short) 2025); 
//        film.setRentalDuration((byte) 5); 
//        film.setRentalRate(new BigDecimal("1.99")); 
//        film.setReplacementCost(new BigDecimal("19.99")); 
//
//        Language englishLanguage = new Language();
//        englishLanguage.setLanguageId(1);  
//        englishLanguage.setName("English");  
//
//        film.setLanguage(englishLanguage);
//        film.setFilmId(1);
//
//        Set<ConstraintViolation<Film>> violations = validator.validate(film);
//
//        assertFalse(violations.isEmpty());
//        assertEquals(1, violations.size()); 
//
//        boolean foundNotEmptyViolation = false;
//
//        for (ConstraintViolation<Film> violation : violations) {
//            if ("El título de la película no puede estar vacío".equals(violation.getMessage())) {
//                foundNotEmptyViolation = true;
//            }
//        }
//
//        assertTrue(foundNotEmptyViolation);
//    }
//    
//    @Test
//    @DisplayName("Título de la película muy largo")
//    public void testLongTitle() {
//        Film film = new Film();
//        film.setTitle("A very long title that exceeds the 128 character limit. " +
//                "This should be enough characters to exceed the limit and " +
//                "cause a validation error related to size.");
////        film.setRating("PG");
//        film.setReleaseYear((short) 2025); 
//        film.setRentalDuration((byte) 5); 
//        film.setRentalRate(new BigDecimal("1.99")); 
//        film.setReplacementCost(new BigDecimal("19.99")); 
//
//        Language englishLanguage = new Language();
//        englishLanguage.setLanguageId(1);  
//        englishLanguage.setName("English");  
//
//        film.setLanguage(englishLanguage);
//        film.setFilmId(1);
//
//        Set<ConstraintViolation<Film>> violations = validator.validate(film);
//
//        assertFalse(violations.isEmpty());
//        assertEquals(1, violations.size()); 
//
//        boolean foundSizeViolation = false;
//
//        for (ConstraintViolation<Film> violation : violations) {
//            if ("El título no puede exceder los 128 caracteres".equals(violation.getMessage())) {
//                foundSizeViolation = true;
//            }
//        }
//
//        assertTrue(foundSizeViolation);
//    }
//    
//    @Test
//    @DisplayName("Rating de la película inválido")
//    public void testRatingInvalid() {
//        Film film = new Film();
//        //film.setRating("INVALID");
//
//        film.setTitle("Test Movie");
//        film.setReleaseYear((short) 2025);
//        film.setRentalDuration((byte) 5); 
//        film.setRentalRate(new BigDecimal("2.99"));
//        film.setReplacementCost(new BigDecimal("10.99")); 
//        
//        Language language = new Language();
//        language.setLanguageId(1);
//        language.setName("English");
//        film.setLanguage(language);
//
//        Set<ConstraintViolation<Film>> violations = validator.validate(film);
//
//        assertFalse(violations.isEmpty());
//        assertEquals(1, violations.size());
//
//        boolean found = false;
//        for (ConstraintViolation<Film> violation : violations) {
//            if ("El rating debe ser uno de los valores: G, PG, PG-13, R, NC-17".equals(violation.getMessage())) {
//                found = true;
//                break;
//            }
//        }
//
//        assertTrue(found, "No se encontró el mensaje de validación para el rating inválido");
//    }
//    
//    @Test
//    @DisplayName("Año de lanzamiento nulo")
//    public void testReleaseYearNull() {
//        Film film = new Film();
//        film.setReleaseYear(null); 
//        
//        film.setTitle("Test Movie");
//        film.setRating("PG");
//        film.setRentalDuration((byte) 5); 
//        film.setRentalRate(new BigDecimal("1.99")); 
//        film.setReplacementCost(new BigDecimal("19.99")); 
//
//        Language englishLanguage = new Language();
//        englishLanguage.setLanguageId(1);  
//        englishLanguage.setName("English");  
//
//        film.setLanguage(englishLanguage);
//        film.setFilmId(1);
//
//        Set<ConstraintViolation<Film>> violations = validator.validate(film);
//
//        assertFalse(violations.isEmpty());
//        assertEquals(1, violations.size()); 
//
//        for (ConstraintViolation<Film> violation : violations) {
//            if ("El año de lanzamiento no puede ser nulo".equals(violation.getMessage())) {
//                return; 
//            }
//        }
//
//        fail("No se encontró el mensaje de validación para el año de lanzamiento nulo");
//    }
//        
//    
//    @Test
//    @DisplayName("Tarifa de alquiler con el valor mínimo")
//    public void testRentalRateMinValue() {
//        Film film = new Film();
//        film.setRentalRate(new BigDecimal("0.00")); 
//        
//        film.setTitle("Test Movie");
//        film.setRating("PG");
//        film.setReleaseYear((short) 2025);
//        film.setRentalDuration((byte) 5); 
//        film.setReplacementCost(new BigDecimal("10.99")); 
//        
//        Language language = new Language();
//        language.setLanguageId(1);
//        language.setName("English");
//        film.setLanguage(language);
//        film.setFilmId(1);
//
//        Set<ConstraintViolation<Film>> violations = validator.validate(film);
//
//        assertFalse(violations.isEmpty());
//        assertEquals(1, violations.size());
//
//      
//        for (ConstraintViolation<Film> violation : violations) {
//            if ("La tarifa de alquiler debe ser mayor que 0".equals(violation.getMessage())) {
//                return; 
//            }
//        }
//
//        fail("No se encontró el mensaje de validación para la tarifa de alquiler");
//    }
//    
//    @Test
//    @DisplayName("Coste de reemplazo con valor mínimo")
//    public void testReplacementCostMinValue() {
//        Film film = new Film();
//        film.setReplacementCost(new BigDecimal("0.00")); 
//        
//        film.setTitle("Test Movie");
//        film.setRating("PG");
//        film.setReleaseYear((short) 2025);
//        film.setRentalDuration((byte) 5); 
//        film.setRentalRate(new BigDecimal("10.99")); 
//        
//        Language language = new Language();
//        language.setLanguageId(1);
//        language.setName("English");
//        film.setLanguage(language);
//        film.setFilmId(1);
//
//        Set<ConstraintViolation<Film>> violations = validator.validate(film);
//
//        assertFalse(violations.isEmpty());
//        assertEquals(1, violations.size());
//
//      
//        for (ConstraintViolation<Film> violation : violations) {
//            if ("El costo de reemplazo debe ser mayor que 0".equals(violation.getMessage())) {
//                return; 
//            }
//        }
//
//        fail("No se encontró el mensaje de validación para el costo de reemplazo");
//    }
//    
//    
//    @Test
//    @DisplayName("Idioma de la película no nulo")
//    public void testLanguageNotNull() {
//        Film film = new Film();
//        film.setLanguage(null); 
//        film.setTitle("Test Movie");
//        film.setRating("PG");
//        film.setReleaseYear((short) 2025);
//        film.setRentalDuration((byte) 5); 
//        film.setRentalRate(new BigDecimal("1.99")); 
//        film.setReplacementCost(new BigDecimal("19.99"));
//
//        Set<ConstraintViolation<Film>> violations = validator.validate(film);
//
//        assertFalse(violations.isEmpty());
//        assertEquals(1, violations.size());
//
//        for (ConstraintViolation<Film> violation : violations) {
//            if ("El idioma no puede ser nulo".equals(violation.getMessage())) {
//                return; 
//            }
//        }
//
//        fail("No se encontró el mensaje de validación para el idioma nulo");
//    }
//    
//    @Test
//    @DisplayName("Tarifa de alquiler con valor negativo")
//    public void testRentalRateNegativeValue() {
//        Film film = new Film();
//        film.setRentalRate(new BigDecimal("-1.00")); 
//        
//        film.setTitle("Test Movie");
//        film.setRating("PG");
//        film.setReleaseYear((short) 2025);
//        film.setRentalDuration((byte) 5); 
//        film.setReplacementCost(new BigDecimal("10.99")); 
//        
//        Language language = new Language();
//        language.setLanguageId(1);
//        language.setName("English");
//        film.setLanguage(language);
//        film.setFilmId(1);
//        
//        Set<ConstraintViolation<Film>> violations = validator.validate(film);
//        
//        assertFalse(violations.isEmpty());
//        assertEquals(1, violations.size());
//    }
//
//    
//
//}
