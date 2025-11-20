package com.inndata.tienda18.service.impl;

import com.inndata.tienda18.entity.Pedido;
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
        p1.setActivo(true);
        Mockito.when(pedidoRepository.save(p1)).thenReturn(p1);
        assertEquals("Departamento actualizado",pedidoService.updateById(1,p1));
    }
    @Test
    void updateByIdElse(){
        Pedido p6= new Pedido();
        Mockito.when(pedidoRepository.findById(6)).thenReturn(Optional.empty());
        assertEquals("No se encuentra el pedido", pedidoService.updateById(6,p6));
    }

    @Test
    void readById() {
        Mockito.when(pedidoRepository.findById(1)).thenReturn(Optional.of(p1));
        Optional<Pedido> result = pedidoService.readById(1);
        assertEquals(p1,result.get());
    }

    @Test
    void delete() {
        Mockito.when(pedidoRepository.findById(1)).thenReturn(Optional.of(p1));
        p1.setActivo(false);
        Mockito.when(pedidoRepository.save(p1)).thenReturn(p1);
        assertEquals("El pedido ha sido borrado", pedidoService.delete(1));
    }
    @Test
    void deleteElse() {
        Mockito.when(pedidoRepository.findById(5)).thenReturn(Optional.empty());
        assertEquals("No existe el pedido",pedidoService.delete(5));
    }
}