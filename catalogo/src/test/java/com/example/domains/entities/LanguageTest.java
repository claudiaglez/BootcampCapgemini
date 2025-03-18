package com.example.domains.entities;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.example.domains.entities.Language;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class LanguageTest {
		
		private Validator validator;

	    @BeforeEach
	    public void setUp() {
	        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	        validator = factory.getValidator();
	    }

	    @Test
	    @DisplayName("Nombre del idioma válido")
	    public void testValidName() {
	        Language language = new Language();
	        language.setName("English");

	        Set<ConstraintViolation<Language>> violations = validator.validate(language);

	        assertTrue(violations.isEmpty());
	    }
	    
	    @Test
	    @DisplayName("Nombre del idioma vacío")
	    public void testEmptyName() {
	        Language language = new Language();
	        language.setName(""); 

	        Set<ConstraintViolation<Language>> violations = validator.validate(language);

	        assertFalse(violations.isEmpty());
	        assertEquals(2, violations.size()); 

	        boolean foundNotEmptyViolation = false;
	        boolean foundSizeViolation = false;

	        for (ConstraintViolation<Language> violation : violations) {
	            if ("El nombre del idioma no puede estar vacío".equals(violation.getMessage())) {
	                foundNotEmptyViolation = true;
	            }
	            if ("El nombre del idioma debe tener entre 3 y 20 caracteres".equals(violation.getMessage())) {
	                foundSizeViolation = true;
	            }
	        }

	        assertTrue(foundNotEmptyViolation);
	        assertTrue(foundSizeViolation);
	    }
	    
	    @Test
	    @DisplayName("Nombre del idioma de tamaño válido")
	    public void testSizeName() {
	        Language language = new Language();
	        language.setName("En"); 

	        Set<ConstraintViolation<Language>> violations = validator.validate(language);

	        assertFalse(violations.isEmpty());
	        assertEquals(1, violations.size()); 

	        assertEquals("El nombre del idioma debe tener entre 3 y 20 caracteres", violations.iterator().next().getMessage());
	    }

		
	}


