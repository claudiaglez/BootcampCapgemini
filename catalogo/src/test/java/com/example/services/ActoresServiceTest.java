package com.example.services;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;

import com.example.domains.contracts.repositories.ActoresRepository;
import com.example.domains.entities.Actor;
import com.example.domains.services.ActoresServiceImpl;

public class ActoresServiceTest {

	 @InjectMocks
	    private ActoresServiceImpl actoresService; 

	    @Mock
	    private ActoresRepository actoresRepository; 

	    private Actor actor1;
	    private Actor actor2;

	    @BeforeEach
	    public void setUp() {
	        MockitoAnnotations.openMocks(this);

	        actor1 = new Actor();
	        actor1.setFirstName("Carlos");
	        actor1.setLastName("Gonzalez");

	        actor2 = new Actor();
	        actor2.setFirstName("Carmen");
	        actor2.setLastName("Lopez");
	    }

	    @Test
	    public void testObtenerActoresPorPrefijo() {
	        when(actoresRepository.findByFirstNameStartingWith("Car")).thenReturn(Arrays.asList(actor1, actor2));

	        List<Actor> result = actoresService.obtenerActoresPorPrefijo("Car");

	        assertNotNull(result);
	        assertEquals(2, result.size());
	        assertTrue(result.contains(actor1));
	        assertTrue(result.contains(actor2));

	        verify(actoresRepository).findByFirstNameStartingWith("Car");
	    }
	    
	    @Test
	    public void testObtenerActoresPorPrefijoNoResultados() {
	        when(actoresRepository.findByFirstNameStartingWith("Joaquin")).thenReturn(Arrays.asList());

	        List<Actor> result = actoresService.obtenerActoresPorPrefijo("Joaquin");

	        assertNotNull(result);
	        assertEquals(0, result.size());
	    }
	    
	    @Test
	    public void testObtenerActoresMayoresQue() {
	        when(actoresRepository.findByActorIdGreaterThan(1)).thenReturn(Arrays.asList(actor2));

	        List<Actor> result = actoresService.obtenerActoresMayoresQue(1);

	        assertNotNull(result);
	        assertEquals(1, result.size()); 
	        assertTrue(result.contains(actor2));

	        verify(actoresRepository).findByActorIdGreaterThan(1);
	    }
	    
	    @Test
	    public void testObtenerActoresPorLetra() {
	    	when(actoresRepository.findByFirstNameStartingWith("C")).thenReturn(Arrays.asList(actor1, actor2));

	        List<Actor> result = actoresService.obtenerActoresPorLetra("C");

	        assertNotNull(result);
	        assertEquals(2, result.size());
	        assertTrue(result.contains(actor1));
	        assertTrue(result.contains(actor2));

	        verify(actoresRepository).findByFirstNameStartingWith("C");
	    }

}
