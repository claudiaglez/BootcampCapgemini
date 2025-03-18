package com.example.domains.entities;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.domains.entities.Category;

public class CategoryTest {

	private Validator validator;
	
	@BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
	
	@Test
	@DisplayName("Nombre de la categoría no nula")
    public void testNameNotNull() {
        Category category = new Category();
        category.setName("Cartoon");

        Set<ConstraintViolation<Category>> violations = validator.validate(category);

        assertTrue(violations.isEmpty());
    }
	
	@Test
	@DisplayName("Nombre de la categoría nula")
	public void testNameNull() {
	    Category category = new Category();
	    category.setName(null);

	    Set<ConstraintViolation<Category>> violations = validator.validate(category);

	    assertFalse(violations.isEmpty());
	    boolean foundNullMessage = violations.stream()
	                                         .anyMatch(v -> "El nombre de la categoría no puede ser nulo".equals(v.getMessage()));
	    assertTrue(foundNullMessage);
	}
	
	@Test
	@DisplayName("Nombre de la categoría vacía")
    public void testEmptyName() {
        Category category = new Category();
        category.setName(""); 

        Set<ConstraintViolation<Category>> violations = validator.validate(category);

        assertFalse(violations.isEmpty());
        assertEquals(2, violations.size()); 

        boolean foundNotEmptyViolation = false;
        boolean foundSizeViolation = false;

        for (ConstraintViolation<Category> violation : violations) {
            if ("El nombre de la categoría no puede estar vacío".equals(violation.getMessage())) {
                foundNotEmptyViolation = true;
            }
            if ("El nombre de la categoría debe tener entre 3 y 25 caracteres".equals(violation.getMessage())) {
                foundSizeViolation = true;
            }
        }

        assertTrue(foundNotEmptyViolation);
        assertTrue(foundSizeViolation);
    }
	
	@Test
	@DisplayName("Tamaño del nombre de la categoría")
    public void testSizeName() {
        Category category = new Category();
        category.setName("En"); 

        Set<ConstraintViolation<Category>> violations = validator.validate(category);

        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size()); 

        assertEquals("El nombre de la categoría debe tener entre 3 y 25 caracteres", violations.iterator().next().getMessage());
    }

	


	
	
	
	

}
