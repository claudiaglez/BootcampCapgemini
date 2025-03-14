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

public class LanguagesServiceTest {
    
    @Mock
    private LanguagesRepository languagesRepository;  
    
    @Mock
    private LanguagesService languagesService;  
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  
    }

    @Test
    void testIdiomasAcabanN() {

        Language language1 = new Language("Spanish");
        Language language2 = new Language("German");
        Language language3 = new Language("Italian");

        when(languagesRepository.findByNameEndingWith("n")).thenReturn(Arrays.asList(language1, language3));

        when(languagesService.idiomasAcabanN("n")).thenReturn(Arrays.asList(language1, language3));

        List<Language> result = languagesService.idiomasAcabanN("n");

        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(language1));
        assertTrue(result.contains(language3));

        verify(languagesRepository).findByNameEndingWith("n");
        verify(languagesService).idiomasAcabanN("n");
    }
}
