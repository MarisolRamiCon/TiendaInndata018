package com.inndata.tienda18.service.impl;

import com.inndata.tienda18.entity.Clientes;
import com.inndata.tienda18.model.request.ClienteRequest;
import com.inndata.tienda18.model.response.ClienteResponse;
import com.inndata.tienda18.repository.IClientesRepository;
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
class ClienteServiceTest {

    @Mock
    private IClientesRepository clientesRepository;

    @InjectMocks
    private ClienteService clienteService;

    @BeforeEach
    void setUp() {
        System.out.println("Setting up before each test");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Tearing down after each test");
    }
    private Clientes buildCliente() {
        Clientes c = new Clientes();
        c.setIdCliente(1);
        c.setNombre("Juan");
        c.setApellido("Pérez");
        c.setCorreo("jp@test.com");
        c.setDireccion("Calle 1");
        c.setTelefono("12345");
        c.setActivo(true);
        return c;
    }
    private ClienteRequest buildRequest() {
        ClienteRequest r = new ClienteRequest();
        r.setNombre("Juan");
        r.setApellido("Pérez");
        r.setCorreo("jp@test.com");
        r.setDireccion("Calle 1");
        r.setTelefono("12345");
        return r;
    }

    @Test
    void readAll() {
        Clientes c = buildCliente();

        when(clientesRepository.findAll()).thenReturn(List.of(c));

        List<ClienteResponse> result = clienteService.readAll();

        assertEquals(1, result.size());
        assertEquals("Juan", result.get(0).getNombre());
        verify(clientesRepository).findAll();
    }

    @Test
    void readById() {
        Clientes c = buildCliente();
        when(clientesRepository.findById(1)).thenReturn(Optional.of(c));

        ClienteResponse result = clienteService.readById(1);

        assertNotNull(result);
        assertEquals("Juan", result.getNombre());
    }
    @Test
    void readById_notFound() {
        when(clientesRepository.findById(1)).thenReturn(Optional.empty());

        ClienteResponse result = clienteService.readById(1);

        assertNull(result);
    }

    @Test
    void create() {
        ClienteRequest req = buildRequest();
        Clientes entity = buildCliente();

        when(clientesRepository.save(any())).thenReturn(entity);

        ClienteResponse result = clienteService.create(req);

        assertNotNull(result);
        assertEquals("Juan", result.getNombre());
        verify(clientesRepository).save(any());
    }

    @Test
    void update() {
        ClienteRequest req = buildRequest();
        Clientes existente = buildCliente();

        when(clientesRepository.findById(1)).thenReturn(Optional.of(existente));
        when(clientesRepository.save(any())).thenReturn(existente);

        ClienteResponse result = clienteService.update(1, req);

        assertNotNull(result);
        verify(clientesRepository).findById(1);
        verify(clientesRepository).save(any());
    }
    @Test
    void update_notFound() {
        ClienteRequest req = buildRequest();

        when(clientesRepository.findById(1)).thenReturn(Optional.empty());

        ClienteResponse result = clienteService.update(1, req);

        assertNull(result);
    }
    @Test
    void update_exception() {
        ClienteRequest req = buildRequest();

        when(clientesRepository.findById(1)).thenThrow(new RuntimeException("Error"));

        ClienteResponse result = clienteService.update(1, req);

        assertNotNull(result);
        assertNull(result.getNombre());
    }

    @Test
    void deleteById() {
        Clientes c = buildCliente();

        when(clientesRepository.findById(1)).thenReturn(Optional.of(c));
        when(clientesRepository.save(any())).thenReturn(c);

        ClienteResponse result = clienteService.deleteById(1);

        assertNotNull(result);
        assertFalse(result.isActivo());
        verify(clientesRepository).findById(1);
        verify(clientesRepository).save(any());
    }
    @Test
    void deleteById_notFound() {
        when(clientesRepository.findById(1)).thenReturn(Optional.empty());

        ClienteResponse result = clienteService.deleteById(1);

        assertNull(result);
    }
    @Test
    void deleteById_exception() {
        when(clientesRepository.findById(1)).thenThrow(new RuntimeException("Error"));

        ClienteResponse result = clienteService.deleteById(1);

        assertNotNull(result);
        assertNull(result.getNombre());
    }

    @Test
    void findByLetra() {
        Clientes c = buildCliente();

        when(clientesRepository.findByNombreStartingWith("J"))
                .thenReturn(List.of(c));

        List<ClienteResponse> result = clienteService.findByLetra("J");

        assertEquals(1, result.size());
    }

    @Test
    void findClienteEliminado() {
        Clientes c = buildCliente();
        c.setActivo(false);

        when(clientesRepository.findByActivoFalse())
                .thenReturn(List.of(c));

        List<ClienteResponse> result = clienteService.findClienteEliminado(false);

        assertEquals(1, result.size());
    }

    @Test
    void telefonoForaneo() {
        Clientes c = buildCliente();

        when(clientesRepository.telefonoForaneo("123"))
                .thenReturn(List.of(c));

        List<ClienteResponse> result = clienteService.telefonoForaneo("123");

        assertEquals(1, result.size());
    }
}