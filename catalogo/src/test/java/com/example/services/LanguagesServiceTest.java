package com.example.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.example.domains.contracts.repositories.LanguagesRepository;
import com.example.domains.contracts.services.LanguagesService;
import com.example.domains.entities.Language;
import com.example.domains.services.LanguagesServiceImpl;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

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
    @DisplayName("Añadir idioma")
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
    @DisplayName("Obtener todos los idiomas")
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
    
    @Test
    @DisplayName("Obtener un idioma")
    void testGetOneLanguage() {
        Language language = new Language();
        language.setName("Spanish");
        
        when(languagesRepository.findById(1)).thenReturn(Optional.of(language));

        Optional<Language> result = languagesService.getOne(1);

        assertTrue(result.isPresent());
        assertEquals(language, result.get());
        
        verify(languagesRepository).findById(1);
    }

    @Test
    @DisplayName("Obtener un idioma no existente")
    void testGetOneLanguageNotFound() {
        when(languagesRepository.findById(999)).thenReturn(Optional.empty());

        Optional<Language> result = languagesService.getOne(999);

        assertFalse(result.isPresent());

        verify(languagesRepository).findById(999);
    }
    
    @Test
    @DisplayName("Modificar idioma")
    void testModifyLanguage() throws NotFoundException, InvalidDataException {

        Language existingLanguage = new Language();
        existingLanguage.setLanguageId(1);  
        existingLanguage.setName("Spanish");

        Language modifiedLanguage = new Language();
        modifiedLanguage.setLanguageId(1); 
        modifiedLanguage.setName("English");

        when(languagesRepository.findById(1)).thenReturn(Optional.of(existingLanguage));

        when(languagesRepository.save(modifiedLanguage)).thenReturn(modifiedLanguage);

        Language result = languagesService.modify(modifiedLanguage);

        assertNotNull(result);
        assertEquals("English", result.getName());

    }

    @Test
    @DisplayName("Modificar idioma no encontrado")
    void testModifyLanguageNotFound() {
        Language modifiedLanguage = new Language();
        modifiedLanguage.setLanguageId(1); 
        modifiedLanguage.setName("English");

        when(languagesRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            languagesService.modify(modifiedLanguage);
        });

        verify(languagesRepository).findById(1);
    }

    @Test
    @DisplayName("Eliminar idioma")
    void testDeleteLanguage() throws InvalidDataException {

        Language language = new Language();
        language.setName("Spanish");

        when(languagesRepository.existsById(1)).thenReturn(true);

        languagesService.delete(language);
        
        verify(languagesRepository).delete(language);
    }
    
    @Test
    @DisplayName("Obtener idiomas que acaban en N")
    public void testIdiomasAcabanN() {
        Language spanish = new Language();
        spanish.setName("Spanish");

        Language english = new Language();
        english.setName("English");

        Language german = new Language();
        german.setName("German");

        List<Language> idiomasMock = Arrays.asList(spanish, english, german);

        when(languagesRepository.findByNameEndingWith("n")).thenReturn(idiomasMock);

        List<Language> result = languagesService.idiomasAcabanN("n");

        assertNotNull(result);
        assertEquals(3, result.size());
        assertTrue(result.stream().anyMatch(idioma -> idioma.getName().equals("Spanish")));
        assertTrue(result.stream().anyMatch(idioma -> idioma.getName().equals("English")));
        assertTrue(result.stream().anyMatch(idioma -> idioma.getName().equals("German")));
    }
    
   
    

}

