package com.inndata.tienda18.service.impl;

import com.inndata.tienda18.entity.DetallePedido;
import com.inndata.tienda18.model.request.DetallePedidoRequest;
import com.inndata.tienda18.model.response.DetallePedidoResponse;
import com.inndata.tienda18.model.response.Message;
import com.inndata.tienda18.repository.DetallePedidoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DetallePedidoServiceTest {

    @Mock
    DetallePedidoRepository detallePedidoRepository;

    @InjectMocks
    DetallePedidoService detallePedidoService;

    // OBJETOS DE PRUEBA
    DetallePedido d1 = new DetallePedido(1, 10, 5, 12.5, 3.0, true);
    DetallePedido d2 = new DetallePedido(2, 20, 3, 15.0, 4.0, false);
    @Test
    void readAll() {
        List<DetallePedido> lista = List.of(d1, d2);
        Mockito.when(detallePedidoRepository.findAll()).thenReturn(lista);

        List<DetallePedidoResponse> result = detallePedidoService.readAll();

        assertEquals(1, result.size());
        assertEquals(d1.getIdDetallePedido(), result.get(0).getId());
        assertEquals(d2.getIdDetallePedido(), result.get(1).getId());
    }
    @Test
    void readById() {
        Mockito.when(detallePedidoRepository.findById(1)).thenReturn(Optional.of(d1));

        Optional<DetallePedidoResponse> result = detallePedidoService.readById(1);

        assertTrue(result.isPresent());
        assertEquals(d1.getIdDetallePedido(), result.get().getId());
    }
    @Test
    void create() {
        DetallePedidoRequest request = new DetallePedidoRequest(
                1,
                10,
                5.0,
                12.5,
                3,
                true);

        Mockito.when(detallePedidoRepository.save(Mockito.any())).thenReturn(d1);

        Message result = detallePedidoService.create(request);

        assertEquals("Detalle del pedido creado correctamente", result.getMessage());
    }
    @Test
    void update() {
        DetallePedidoRequest request =
                new DetallePedidoRequest(1, 20, 7.0, 14.0, 9, true);

        Mockito.when(detallePedidoRepository.findById(1)).thenReturn(Optional.of(d1));

        Message result = detallePedidoService.update(1, request);

        assertEquals("Detalle del pedido actualizado", result.getMessage());
    }
    @Test
    void updateElse() {
        DetallePedidoRequest request =
                new DetallePedidoRequest(1, 20, 7.0, 14.0, 9, true);

        Mockito.when(detallePedidoRepository.findById(1)).thenReturn(Optional.empty());

        Message result = detallePedidoService.update(1, request);

        assertEquals("No se encuentran esos detalles del pedido", result.getMessage());
    }
    @Test
    void updateById() {
        DetallePedido nuevo = new DetallePedido(1, 10, 4, 8.0, 5.0, true);

        Mockito.when(detallePedidoRepository.findById(1)).thenReturn(Optional.of(d1));
        Mockito.when(detallePedidoRepository.save(Mockito.any())).thenReturn(d1);

        String result = detallePedidoService.updateById(1, nuevo);

        assertEquals("Detalle pedido actualizado", result);
    }
    @Test
    void delete() {
        Mockito.when(detallePedidoRepository.findById(1)).thenReturn(Optional.of(d1));
        Mockito.when(detallePedidoRepository.save(Mockito.any())).thenReturn(d1);

        Message result = detallePedidoService.delete(1);

        assertEquals("Los detalles del pedido han sido borrados", result.getMessage());
    }
    @Test
    void deleteElse() {
        Mockito.when(detallePedidoRepository.findById(1))
                .thenReturn(Optional.empty());

        Message result = detallePedidoService.delete(1);

        assertEquals("No existen esos detalles", result.getMessage());
    }

}
