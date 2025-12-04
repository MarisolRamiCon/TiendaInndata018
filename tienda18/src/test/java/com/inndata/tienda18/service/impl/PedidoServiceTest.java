package com.inndata.tienda18.service.impl;

import com.inndata.tienda18.entity.Pedido;
import com.inndata.tienda18.model.request.PedidoRequest;
import com.inndata.tienda18.model.response.Message;
import com.inndata.tienda18.model.response.PedidoResponse;
import com.inndata.tienda18.repository.PedidoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class PedidoServiceTest {

    Pedido p1= new Pedido(1,2, LocalDate.parse("2025-11-11"),450.9,true);
    Pedido p2 = new Pedido(2, 4, LocalDate.parse("2025-11-15"), 320.0, false);

    @InjectMocks
    PedidoService pedidoService;
    @Mock
    PedidoRepository pedidoRepository;
    @BeforeEach
    void setUp() {
        System.out.println("Se ejecuta el before");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Se ejecuta el after");
    }
    @Test
    void updateById() {
        Mockito.when(pedidoRepository.findById(1)).thenReturn(Optional.of(p1));

        p1.setIdCliente(2);
        p1.setFechaPedido(LocalDate.parse("2025-12-12"));
        p1.setTotalPedido(234.3);

        Mockito.when(pedidoRepository.save(p1)).thenReturn(p1);

        assertEquals("Departamento actualizado", pedidoService.updateById(1, p1));
    }

    @Test
    void updateByIdElse() {
        Mockito.when(pedidoRepository.findById(6)).thenReturn(Optional.empty());

        Pedido p6 = new Pedido();
        assertEquals("No se encuentra el pedido", pedidoService.updateById(6, p6));
    }
    @Test
    void readById() {
        Mockito.when(pedidoRepository.findById(1)).thenReturn(Optional.of(p1));

        var result = pedidoService.readById(1);

        assertTrue(result.isPresent());
        assertEquals(p1.getId(), result.get().getId());
        assertEquals(p1.getIdCliente(), result.get().getIdCliente());
    }
    @Test
    void delete() {
        Mockito.when(pedidoRepository.findById(1)).thenReturn(Optional.of(p1));
        Mockito.doNothing().when(pedidoRepository).deleteById(1);

        p1.setActivo(false);
        Mockito.when(pedidoRepository.save(p1)).thenReturn(p1);

        Message msg = pedidoService.delete(1);

        assertEquals("El pedido ha sido eliminado", msg.getMessage());
    }
    @Test
    void deleteElse() {
        Mockito.when(pedidoRepository.findById(5)).thenReturn(Optional.empty());

        Message msg = pedidoService.delete(5);

        assertEquals("No existe el pedido", msg.getMessage());
    }

    @Test
    void readAll() {
        List<Pedido> lista = List.of(p1, p2);

        Mockito.when(pedidoRepository.findAll()).thenReturn(lista);

        List<PedidoResponse> result = pedidoService.readAll();

        // Validar el primer pedido
        assertEquals(p1.getId(), result.get(0).getId());
        assertEquals(p1.getIdCliente(), result.get(0).getIdCliente());
        assertEquals(p1.getFechaPedido(), result.get(0).getFechaPedido());
        assertEquals(p1.getTotalPedido(), result.get(0).getTotalPedido());
        assertEquals(p1.getActivo(), result.get(0).getActivo());

        // Validar el segundo pedido
        assertEquals(p2.getId(), result.get(1).getId());
        assertEquals(p2.getIdCliente(), result.get(1).getIdCliente());
        assertEquals(p2.getFechaPedido(), result.get(1).getFechaPedido());
        assertEquals(p2.getTotalPedido(), result.get(1).getTotalPedido());
        assertEquals(p2.getActivo(), result.get(1).getActivo());
    }

    @Test
    void update() {
        PedidoRequest request = new PedidoRequest();
        request.setIdCliente(10);
        request.setFechaPedido(LocalDate.parse("2025-12-01"));
        request.setTotalPedido(999.9);

        Mockito.when(pedidoRepository.findById(1)).thenReturn(Optional.of(p1));
        Mockito.when(pedidoRepository.save(Mockito.any(Pedido.class))).thenReturn(p1);

        Message msg = pedidoService.update(1, request);

        assertEquals("Pedido actualizado correctamente", msg.getMessage());
    }
    @Test
    void updateElse() {
        PedidoRequest request = new PedidoRequest();
        Mockito.when(pedidoRepository.findById(10)).thenReturn(Optional.empty());

        Message msg = pedidoService.update(10, request);

        assertEquals("No se encuentra el pedido", msg.getMessage());
    }
    @Test
    void create() {
        PedidoRequest request = new PedidoRequest();
        request.setIdCliente(5);
        request.setFechaPedido(LocalDate.parse("2025-10-10"));
        request.setTotalPedido(450.0);

        Mockito.when(pedidoRepository.save(Mockito.any(Pedido.class))).thenReturn(p1);

        Message msg = pedidoService.create(request);

        assertEquals("Pedido creado correctamente", msg.getMessage());
    }
}