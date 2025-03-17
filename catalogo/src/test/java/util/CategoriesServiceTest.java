package util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.domains.contracts.repositories.CategoriesRepository;
import com.example.domains.entities.Category;
import com.example.domains.services.CategoriesServiceImpl;



public class CategoriesServiceTest {

	@InjectMocks
    private CategoriesServiceImpl categoriesService; 

    @Mock
    private CategoriesRepository categoriesRepository; 

    private Category category1;
    private Category category2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        category1 = new Category();
        category1.setName("Cartoon");

        category2 = new Category();
        category2.setName("Voyages");
    }
    
    @Test
    public void testObtenerCategoriasMayoresQue() {
        when(categoriesRepository.findByCategoryIdGreaterThan(1)).thenReturn(Arrays.asList(category2));

        List<Category> result = categoriesService.obtenerCategoriasMayoresQue(1);

        assertNotNull(result);
        assertEquals(1, result.size()); 
        assertTrue(result.contains(category2));

        verify(categoriesRepository).findByCategoryIdGreaterThan(1);
    }

}
