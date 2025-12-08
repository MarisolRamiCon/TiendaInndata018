package com.inndata.tienda18.controller;

import com.inndata.tienda18.model.request.ClienteRequest;
import com.inndata.tienda18.model.response.ClienteResponse;
import com.inndata.tienda18.service.impl.ClienteService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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
        sampleResponse = new ClienteResponse();
        sampleRequest = new ClienteRequest();
        sampleRequest.setNombre("Juan");
        sampleRequest.setApellido("Perez");
    }

    @AfterEach void tearDown() { System.out.println("Tearing down after each test"); }

    // ----------------------------------------------------------------------
    // READ ALL
    // ----------------------------------------------------------------------
    @Test
    void readAll_returnsList() {
        when(clienteService.readAll()).thenReturn(List.of(sampleResponse));

        ResponseEntity<List<ClienteResponse>> response = clienteController.readAll();

        assertEquals(200, response.getStatusCode().value());
        assertEquals(1, response.getBody().size());
        verify(clienteService).readAll();
    }

    @Test
    void readAll_whenServiceThrowsException_returns500() {
        when(clienteService.readAll()).thenThrow(new RuntimeException("Error"));

        ResponseEntity<List<ClienteResponse>> response = clienteController.readAll();

        assertEquals(500, response.getStatusCode().value());
        assertNull(response.getBody());
        verify(clienteService).readAll();
    }

    // ----------------------------------------------------------------------
    // READ BY ID
    // ----------------------------------------------------------------------
    @Test
    void readById_existingId_returnsOk() {
        when(clienteService.readById(1)).thenReturn(sampleResponse);

        ResponseEntity<ClienteResponse> response = clienteController.readById(1);

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
    }

    @Test
    void readById_nonExistingId_returnsNotFound() {
        when(clienteService.readById(99)).thenReturn(null);

        ResponseEntity<ClienteResponse> response = clienteController.readById(99);

        assertEquals(404, response.getStatusCode().value());
        assertNull(response.getBody());
    }

    @Test
    void readById_whenServiceThrowsException_returns500() {
        when(clienteService.readById(1)).thenThrow(new RuntimeException("Error"));

        ResponseEntity<ClienteResponse> response = clienteController.readById(1);

        assertEquals(500, response.getStatusCode().value());
        assertNull(response.getBody());
    }

    // ----------------------------------------------------------------------
    // CREATE
    // ----------------------------------------------------------------------
    @Test
    void create_success_returnsOk() {
        when(clienteService.create(sampleRequest)).thenReturn(sampleResponse);

        ResponseEntity<ClienteResponse> response = clienteController.create(sampleRequest);

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
    }

    @Test
    void create_whenServiceThrowsException_returns500() {
        when(clienteService.create(sampleRequest)).thenThrow(new RuntimeException("Error"));

        ResponseEntity<ClienteResponse> response = clienteController.create(sampleRequest);

        assertEquals(500, response.getStatusCode().value());
        assertNull(response.getBody());
    }

    // ----------------------------------------------------------------------
    // UPDATE
    // ----------------------------------------------------------------------
    @Test
    void update_existingId_returnsOk() {
        when(clienteService.update(1, sampleRequest)).thenReturn(sampleResponse);

        ResponseEntity<ClienteResponse> response = clienteController.update(1, sampleRequest);

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
    }

    @Test
    void update_nonExistingId_returnsNotFound() {
        when(clienteService.update(99, sampleRequest)).thenReturn(null);

        ResponseEntity<ClienteResponse> response = clienteController.update(99, sampleRequest);

        assertEquals(404, response.getStatusCode().value());
        assertNull(response.getBody());
    }

    @Test
    void update_whenServiceThrowsException_returns500() {
        when(clienteService.update(1, sampleRequest)).thenThrow(new RuntimeException("Error"));

        ResponseEntity<ClienteResponse> response = clienteController.update(1, sampleRequest);

        assertEquals(500, response.getStatusCode().value());
        assertNull(response.getBody());
    }

    // ----------------------------------------------------------------------
    // DELETE
    // ----------------------------------------------------------------------
    @Test
    void delete_existingId_returnsOk() {
        when(clienteService.deleteById(1)).thenReturn(sampleResponse);

        ResponseEntity<ClienteResponse> response = clienteController.delete(1);

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
    }

    @Test
    void delete_nonExistingId_returnsNotFound() {
        when(clienteService.deleteById(99)).thenReturn(null);

        ResponseEntity<ClienteResponse> response = clienteController.delete(99);

        assertEquals(404, response.getStatusCode().value());
        assertNull(response.getBody());
    }

    @Test
    void delete_whenServiceThrowsException_returns500() {
        when(clienteService.deleteById(1)).thenThrow(new RuntimeException("Error"));

        ResponseEntity<ClienteResponse> response = clienteController.delete(1);

        assertEquals(500, response.getStatusCode().value());
        assertNull(response.getBody());
    }

    // ----------------------------------------------------------------------
    // FIND BY LETRA
    // ----------------------------------------------------------------------
    @Test
    void findByLetra_returnsOk() {
        when(clienteService.findByLetra("J")).thenReturn(List.of(sampleResponse));

        ResponseEntity<List<ClienteResponse>> response = clienteController.findByLetra("J");

        assertEquals(200, response.getStatusCode().value());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void findByLetra_whenServiceThrowsException_returns500() {
        when(clienteService.findByLetra("J")).thenThrow(new RuntimeException("Error"));

        ResponseEntity<List<ClienteResponse>> response = clienteController.findByLetra("J");

        assertEquals(500, response.getStatusCode().value());
        assertNull(response.getBody());
    }

    // ----------------------------------------------------------------------
    // TELEFONO FORANEO
    // ----------------------------------------------------------------------
    @Test
    void telefonoForaneo_returnsOk() {
        when(clienteService.telefonoForaneo("555"))
                .thenReturn(List.of(sampleResponse));

        ResponseEntity<List<ClienteResponse>> response =
                clienteController.telefonoForaneo("555");

        assertEquals(200, response.getStatusCode().value());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void telefonoForaneo_whenServiceThrowsException_returns500() {
        when(clienteService.telefonoForaneo("555"))
                .thenThrow(new RuntimeException("Error"));

        ResponseEntity<List<ClienteResponse>> response =
                clienteController.telefonoForaneo("555");

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }

    // ----------------------------------------------------------------------
    // CLIENTE ELIMINADO
    // ----------------------------------------------------------------------
    @Test
    void findClienteEliminado_returnsOk() {
        when(clienteService.findClienteEliminado(false))
                .thenReturn(List.of(sampleResponse));

        ResponseEntity<List<ClienteResponse>> response =
                clienteController.findClienteEliminado();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void findClienteEliminado_whenServiceThrowsException_returns500() {
        when(clienteService.findClienteEliminado(false))
                .thenThrow(new RuntimeException("Error"));

        ResponseEntity<List<ClienteResponse>> response =
                clienteController.findClienteEliminado();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }
}
