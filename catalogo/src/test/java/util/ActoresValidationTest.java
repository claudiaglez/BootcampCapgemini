package util;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.domains.entities.Actor;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import static org.junit.jupiter.api.Assertions.*;

public class ActoresValidationTest {

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


}
