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

import com.inndata.tienda18.feign.ProveedoresClient;
import com.inndata.tienda18.model.Proveedores;


@ExtendWith(MockitoExtension.class)
class ProveedoresFeignServiceTest {

    @Mock
    private ProveedoresClient proveedoresClient;

    @InjectMocks
    private ProveedoresFeignService proveedorFeignService; 

    @Test
    void testCreate() {
        Proveedores proveedor = new Proveedores();
        Mockito.when(proveedoresClient.create(proveedor)).thenReturn(proveedor);

        Proveedores resultado = proveedorFeignService.create(proveedor);

        assertNotNull(proveedor);
        assertSame(resultado, proveedor);
        Mockito.verify(proveedoresClient).create(resultado);
        

    }

    @Test
    void testDelete() {
        // Arrange
        Mockito.when(proveedoresClient.delete(1)).thenReturn("Eliminado");

        // Act
        String resultado = proveedorFeignService.delete(1);

        // Assert
        assertEquals("Eliminado", resultado);
        Mockito.verify(proveedoresClient).delete(1);


    }

    @Test
    void testReadAll() {
        // Arrange
        Proveedores proveedor = new Proveedores();
        List<Proveedores> lista = List.of(proveedor);

        Mockito.when(proveedoresClient.readAll()).thenReturn(lista);

        // Act
        List<Proveedores> resultado = proveedorFeignService.readAll();

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertTrue(resultado.contains(proveedor));
        Mockito.verify(proveedoresClient).readAll();
        

    }

    @Test
    void testReadById() {
        // Arrange
        Optional<Proveedores> proveedor = Optional.of(new Proveedores());
        Mockito.when(proveedoresClient.readById(1)).thenReturn(proveedor);

        // Act
        Optional<Proveedores> resultado = proveedorFeignService.readById(1);

        // Assert
        assertNotNull(resultado);
        assertEquals(proveedor, resultado);
        Mockito.verify(proveedoresClient).readById(1);
        
    }

    @Test
    void testUpdateById() {
        // Arrange
        Proveedores proveedor = new Proveedores();
        Mockito.when(proveedoresClient.updateById(1, proveedor)).thenReturn("Actualizado");

        // Act
        String resultado = proveedorFeignService.updateById(1, proveedor);

        // Assert
        assertEquals("Actualizado", resultado);
        Mockito.verify(proveedoresClient).updateById(1, proveedor);


    }
}
