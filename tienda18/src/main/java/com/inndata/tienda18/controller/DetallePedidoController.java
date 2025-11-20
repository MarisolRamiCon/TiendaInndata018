package com.inndata.tienda18.controller;

import com.inndata.tienda18.entity.DetallePedido;
import com.inndata.tienda18.service.impl.DetallePedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class DetallePedidoController {
    //Esto es una prueba
    @Autowired
    DetallePedidoService detallePedidoService;
    @GetMapping("/pedido")
    public List<DetallePedido> readAll(){
        return detallePedidoService.readAll();
    }
}
