package com.inndata.tienda18.controller;

import com.inndata.tienda18.model.ClienteExternoModel;
import com.inndata.tienda18.service.impl.ClienteExternoService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteExternoControlerTest {
    @Mock
    private ClienteExternoService clienteExternoService;

    @InjectMocks
    private ClienteExternoControler clienteExternoControler;

    @BeforeEach
    void setUp() {
        System.out.println("Setting up before each test");
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
        System.out.println("Tearing down after each test");
    }

    @Test
    void readAll() {
        ClienteExternoModel c1 = new ClienteExternoModel();
        ClienteExternoModel c2 = new ClienteExternoModel();
        when(clienteExternoService.readAll()).thenReturn(List.of(c1, c2));

        List<ClienteExternoModel> result = clienteExternoControler.readAll();

        assertEquals(2, result.size());
        verify(clienteExternoService, times(1)).readAll();
    }

    @Test
    void readById_existingId_returnsOptional() {
        ClienteExternoModel c = new ClienteExternoModel();
        when(clienteExternoService.readById(1)).thenReturn(Optional.of(c));

        Optional<ClienteExternoModel> result = clienteExternoControler.readById(1);

        assertTrue(result.isPresent());
        verify(clienteExternoService, times(1)).readById(1);
    }

    @Test
    void readById_nonExistingId_returnsEmpty() {
        when(clienteExternoService.readById(99)).thenReturn(Optional.empty());

        Optional<ClienteExternoModel> result = clienteExternoControler.readById(99);

        assertTrue(result.isEmpty());
        verify(clienteExternoService, times(1)).readById(99);
    }

    @Test
    void create_callsServiceAndReturnsModel() {
        ClienteExternoModel input = new ClienteExternoModel();
        ClienteExternoModel output = new ClienteExternoModel();
        when(clienteExternoService.create(input)).thenReturn(output);

        ClienteExternoModel result = clienteExternoControler.create(input);

        assertNotNull(result);
        verify(clienteExternoService, times(1)).create(input);
    }

    @Test
    void updateById_callsServiceAndReturnsModel() {
        ClienteExternoModel input = new ClienteExternoModel();
        ClienteExternoModel output = new ClienteExternoModel();
        when(clienteExternoService.updateById(1, input)).thenReturn(output);

        ClienteExternoModel result = clienteExternoControler.updateById(1, input);

        assertNotNull(result);
        verify(clienteExternoService, times(1)).updateById(1, input);
    }

    @Test
    void deleteById_callsServiceAndReturnsString() {
        when(clienteExternoService.deleteById(1)).thenReturn("Deleted");

        String result = clienteExternoControler.deleteById(1);

        assertEquals("Deleted", result);
        verify(clienteExternoService, times(1)).deleteById(1);
    }
}