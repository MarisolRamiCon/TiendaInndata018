package com.inndata.tienda18.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.inndata.tienda18.Feign.ProductosClient;
import com.inndata.tienda18.model.Productos;

@ExtendWith(MockitoExtension.class)

class ProductosFeignServiceTest {
    @Mock
    private ProductosClient productosClient;

    @InjectMocks
    private ProductosFeignService productosFeignService; // Reemplaza por el nombre real si es distinto

    @Test
    void readAll_shouldReturnListFromClient() {
        // Arrange
        Productos producto = new Productos();
        List<Productos> lista = List.of(producto);

        Mockito.when(productosClient.readAll()).thenReturn(lista);

        // Act
        List<Productos> resultado = productosFeignService.readAll();

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertTrue(resultado.contains(producto));
        Mockito.verify(productosClient).readAll();
    }

    @Test
    void readById_shouldReturnOptionalFromClient() {
        // Arrange
        Productos producto = new Productos();
        Mockito.when(productosClient.readById(1)).thenReturn(Optional.of(producto));

        // Act
        Optional<Productos> resultado = productosFeignService.readById(1);

        // Assert
        assertTrue(resultado.isPresent());
        assertEquals(producto, resultado.get());
        Mockito.verify(productosClient).readById(1);
    }

    @Test
    void create_shouldDelegateToClient() {
        // Arrange
        Productos input = new Productos();
        Productos output = new Productos();
        Mockito.when(productosClient.create(input)).thenReturn(output);

        // Act
        Productos resultado = productosFeignService.create(input);

        // Assert
        assertNotNull(resultado);
        assertSame(output, resultado);
        Mockito.verify(productosClient).create(input);
    }

    @Test
    void updateById_shouldReturnMessageFromClient() {
        // Arrange
        Productos producto = new Productos();
        Mockito.when(productosClient.updateById(1, producto)).thenReturn("Actualizado");

        // Act
        String resultado = productosFeignService.updateById(1, producto);

        // Assert
        assertEquals("Actualizado", resultado);
        Mockito.verify(productosClient).updateById(1, producto);
    }

    @Test
    void delete_shouldReturnMessageFromClient() {
        // Arrange
        Mockito.when(productosClient.delete(1)).thenReturn("Eliminado");

        // Act
        String resultado = productosFeignService.delete(1);

        // Assert
        assertEquals("Eliminado", resultado);
        Mockito.verify(productosClient).delete(1);
    }
}
