package com.inndata.tienda18.service.impl;

import com.inndata.tienda18.feign.ClienteExternoFeign;
import com.inndata.tienda18.model.ClienteExternoModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)

class ClienteExternoServiceTest {

    @Mock
    private ClienteExternoFeign clienteExternoFeign;

    @InjectMocks
    private ClienteExternoService clienteExternoService;

    private ClienteExternoModel cliente;

    @BeforeEach
    void setUp() {
        System.out.println("Setting up before each test");
        cliente = new ClienteExternoModel();
        cliente.setIdCteExt(1);
        cliente.setNombreExt("Juan");
        cliente.setApellidoExt("Perez");
        cliente.setDireccionExt("Calle Falsa 123");
        cliente.setCorreoExt("juan.perez@email.com");
        cliente.setTelefonoExt("5551234");
        cliente.setActivoExt(true);
    }

    @AfterEach
    void tearDown() {
        System.out.println("Tearing down after each test");
    }

    @Test
    void readAll_returnsList() {
        when(clienteExternoFeign.readAll()).thenReturn(List.of(cliente));

        List<ClienteExternoModel> result = clienteExternoService.readAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(clienteExternoFeign, times(1)).readAll();
    }

    @Test
    void readById_existingId_returnsOptional() {
        when(clienteExternoFeign.readById(1)).thenReturn(Optional.of(cliente));

        Optional<ClienteExternoModel> result = clienteExternoService.readById(1);

        assertTrue(result.isPresent());
        assertEquals("Juan", result.get().getNombreExt());
        verify(clienteExternoFeign, times(1)).readById(1);
    }

    @Test
    void create_returnsCreatedCliente() {
        when(clienteExternoFeign.create(cliente)).thenReturn(cliente);

        ClienteExternoModel result = clienteExternoService.create(cliente);

        assertNotNull(result);
        assertEquals("Juan", result.getNombreExt());
        verify(clienteExternoFeign, times(1)).create(cliente);
    }

    @Test
    void updateById_existingCliente_updatesAndReturns() {
        ClienteExternoModel updatedData = new ClienteExternoModel();
        updatedData.setNombreExt("Pedro");
        updatedData.setApellidoExt("Gomez");

        when(clienteExternoFeign.readById(1)).thenReturn(Optional.of(cliente));
        when(clienteExternoFeign.updateById(eq(1), any(ClienteExternoModel.class))).thenReturn(cliente);

        ClienteExternoModel result = clienteExternoService.updateById(1, updatedData);

        assertNotNull(result);
        assertEquals("Pedro", result.getNombreExt());
        assertEquals("Gomez", result.getApellidoExt());
        verify(clienteExternoFeign, times(1)).readById(1);
        verify(clienteExternoFeign, times(1)).updateById(eq(1), any(ClienteExternoModel.class));
    }

    @Test
    void updateById_nonExistingCliente_returnsNewClienteExternoModel() {
        when(clienteExternoFeign.readById(99)).thenReturn(Optional.empty());

        ClienteExternoModel result = clienteExternoService.updateById(99, cliente);

        assertNotNull(result);
        assertNull(result.getIdCteExt());
        verify(clienteExternoFeign, times(1)).readById(99);
        verify(clienteExternoFeign, never()).updateById(anyInt(), any());
    }

    @Test
    void deleteById_existingCliente_returnsSuccessMessage() {
        when(clienteExternoFeign.readById(1)).thenReturn(Optional.of(cliente));
        when(clienteExternoFeign.updateById(eq(1), any(ClienteExternoModel.class))).thenReturn(cliente);

        String result = clienteExternoService.deleteById(1);

        assertEquals("El cliente ha sido eliminado", result);
        verify(clienteExternoFeign, times(1)).readById(1);
        verify(clienteExternoFeign, times(1)).updateById(eq(1), any(ClienteExternoModel.class));
    }

    @Test
    void deleteById_nonExistingCliente_returnsNoExisteMessage() {
        when(clienteExternoFeign.readById(99)).thenReturn(Optional.empty());

        String result = clienteExternoService.deleteById(99);

        assertEquals("No existe ese cliente", result);
        verify(clienteExternoFeign, times(1)).readById(99);
        verify(clienteExternoFeign, never()).updateById(anyInt(), any());
    }
}