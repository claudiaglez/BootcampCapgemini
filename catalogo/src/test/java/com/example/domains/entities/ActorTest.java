package com.example.domains.entities;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.domains.entities.Actor;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import static org.junit.jupiter.api.Assertions.*;

public class ActorTest {

	private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testFirstNameNotNullAndValidSize() {
        Actor actor = new Actor();
        actor.setFirstName("Daniel");
        actor.setLastName("Gonzalez");

        Set<ConstraintViolation<Actor>> violations = validator.validate(actor);

        assertTrue(violations.isEmpty());
    }
    
    @Test
    public void testFirstNameNull() {
        Actor actor = new Actor();
        actor.setFirstName(null); 
        actor.setLastName("Gonzalez");

        Set<ConstraintViolation<Actor>> violations = validator.validate(actor);

        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("El nombre no puede ser nulo.", violations.iterator().next().getMessage());
    }
    
    @Test
    public void testFirstNameTooShort() {
        Actor actor = new Actor();
        actor.setFirstName("D"); 
        actor.setLastName("Gonzalez");

        Set<ConstraintViolation<Actor>> violations = validator.validate(actor);

        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("El nombre debe tener entre 2 y 45 caracteres.", violations.iterator().next().getMessage());
    }
    
    @Test
    public void testFirstNameWithValidCharacters() {
        Actor actor = new Actor();
        actor.setFirstName("José Luis");
        actor.setLastName("González");

        Set<ConstraintViolation<Actor>> violations = validator.validate(actor);

        assertTrue(violations.isEmpty());
    }


}
