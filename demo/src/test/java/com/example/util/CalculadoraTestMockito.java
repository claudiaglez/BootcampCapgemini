package com.example.util;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CalculadoraTestMockito {

    @Test
    void testSuma() {
        ICalculadora calculadoraMock = mock(ICalculadora.class);
        when(calculadoraMock.sumar(2, 3)).thenReturn(5);
        int resultado = calculadoraMock.sumar(2, 3);
        assertEquals(5, resultado);
        verify(calculadoraMock).sumar(2, 3);
    }

    @Test
    void testResta() {
        ICalculadora calculadoraMock = mock(ICalculadora.class);
        when(calculadoraMock.restar(5, 3)).thenReturn(2);
        int resultado = calculadoraMock.restar(5, 3);
        assertEquals(2, resultado);
        verify(calculadoraMock).restar(5, 3);
    }
}


