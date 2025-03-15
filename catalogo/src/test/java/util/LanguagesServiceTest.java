package util;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;

import com.example.domains.contracts.repositories.LanguagesRepository;
import com.example.domains.contracts.services.LanguagesService;
import com.example.domains.entities.Language;
import com.example.domains.services.LanguagesServiceImpl;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;

public class LanguagesServiceTest {

    @Mock
    private LanguagesRepository languagesRepository; 

    @InjectMocks
    private LanguagesServiceImpl languagesService;  

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); 
    }

    @Test
    void testAddLanguage() throws DuplicateKeyException, InvalidDataException {
        Language language = new Language();
        language.setName("Spanish");

        when(languagesRepository.existsById(anyInt())).thenReturn(false);
        when(languagesRepository.save(language)).thenReturn(language);

        Language result = languagesService.add(language);

        assertNotNull(result);
        assertEquals(language.getName(), result.getName());

        verify(languagesRepository).existsById(anyInt());
        verify(languagesRepository).save(language);
    }
    
    @Test
    void testGetAllLanguages() {
        Language language1 = new Language();
        language1.setName("Spanish");
        
        Language language2 = new Language();
        language2.setName("English");

        when(languagesRepository.findAll()).thenReturn(Arrays.asList(language1, language2));

        List<Language> result = languagesService.getAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(language1));
        assertTrue(result.contains(language2));
        
        verify(languagesRepository).findAll();
    }
    
    



}

