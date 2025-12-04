package com.inndata.tienda18.controller;

import com.inndata.tienda18.model.request.ClienteRequest;
import com.inndata.tienda18.model.response.ClienteResponse;
import com.inndata.tienda18.service.impl.ClienteService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)

class ClienteControllerTest {

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private ClienteController clienteController;

    private ClienteResponse sampleResponse;
    private ClienteRequest sampleRequest;

    @BeforeEach
    void setUp() {
        System.out.println("Setting up before each test");
        sampleResponse = new ClienteResponse();
        sampleRequest = new ClienteRequest();
        sampleRequest.setNombre("Juan");
        sampleRequest.setApellido("Perez");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Tearing down after each test");
    }

    @Test
    void readAll_returnsList() {
        when(clienteService.readAll()).thenReturn(List.of(sampleResponse));

        ResponseEntity<List<ClienteResponse>> response = clienteController.readAll();

        assertNotNull(response);
        assertEquals(1, response.getBody().size());
        verify(clienteService, times(1)).readAll();
    }

    @Test
    void readById_existingId_returnsOk() {
        when(clienteService.readById(1)).thenReturn(sampleResponse);

        ResponseEntity<ClienteResponse> response = clienteController.readById(1);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        verify(clienteService, times(1)).readById(1);
    }

    @Test
    void readById_nonExistingId_returnsNotFound() {
        when(clienteService.readById(99)).thenReturn(null);

        ResponseEntity<ClienteResponse> response = clienteController.readById(99);

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
        verify(clienteService, times(1)).readById(99);
    }

    @Test
    void create_success_returnsOk() {
        when(clienteService.create(sampleRequest)).thenReturn(sampleResponse);

        ResponseEntity<ClienteResponse> response = clienteController.create(sampleRequest);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        verify(clienteService, times(1)).create(sampleRequest);
    }

    @Test
    void update_existingId_returnsOk() {
        when(clienteService.update(1, sampleRequest)).thenReturn(sampleResponse);

        ResponseEntity<ClienteResponse> response = clienteController.update(1, sampleRequest);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        verify(clienteService, times(1)).update(1, sampleRequest);
    }

    @Test
    void update_nonExistingId_returnsNotFound() {
        when(clienteService.update(99, sampleRequest)).thenReturn(null);

        ResponseEntity<ClienteResponse> response = clienteController.update(99, sampleRequest);

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
        verify(clienteService, times(1)).update(99, sampleRequest);
    }

    @Test
    void delete_existingId_returnsOk() {
        when(clienteService.deleteById(1)).thenReturn(sampleResponse);

        ResponseEntity<ClienteResponse> response = clienteController.delete(1);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        verify(clienteService, times(1)).deleteById(1);
    }

    @Test
    void delete_nonExistingId_returnsNotFound() {
        when(clienteService.deleteById(99)).thenReturn(null);

        ResponseEntity<ClienteResponse> response = clienteController.delete(99);

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
        verify(clienteService, times(1)).deleteById(99);
    }

    @Test
    void findByLetra_returnsOk() {
        when(clienteService.findByLetra("J")).thenReturn(List.of(sampleResponse));

        ResponseEntity<List<ClienteResponse>> response = clienteController.findByLetra("J");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        verify(clienteService, times(1)).findByLetra("J");
    }

    @Test
    void telefonoForaneo_returnsOk() {
        when(clienteService.telefonoForaneo("555")).thenReturn(List.of(sampleResponse));

        ResponseEntity<List<ClienteResponse>> response = clienteController.telefonoForaneo("555");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        verify(clienteService, times(1)).telefonoForaneo("555");
    }

    @Test
    void findClienteEliminado_returnsOk() {
        when(clienteService.findClienteEliminado(false)).thenReturn(List.of(sampleResponse));

        ResponseEntity<List<ClienteResponse>> response = clienteController.findClienteEliminado();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        verify(clienteService, times(1)).findClienteEliminado(false);
    }

}