package util;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.example.domains.entities.Language;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class LanguagesValidationTest {
		
		private Validator validator;

	    @BeforeEach
	    public void setUp() {
	        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	        validator = factory.getValidator();
	    }

	    @Test
	    public void testValidName() {
	        Language language = new Language();
	        language.setName("English");

	        Set<ConstraintViolation<Language>> violations = validator.validate(language);

	        assertTrue(violations.isEmpty());
	    }
		
	}


