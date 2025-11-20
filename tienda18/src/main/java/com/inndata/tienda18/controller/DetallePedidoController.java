package com.inndata.tienda18.controller;

import com.inndata.tienda18.entity.DetallePedido;
import com.inndata.tienda18.model.DetallePedidoDto;
import com.inndata.tienda18.service.impl.DetallePedidoService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class DetallePedidoController {
    //Esto es una prueba
    @Autowired
    DetallePedidoService detallePedidoService;
    @GetMapping("/detallePedido")
    public List<DetallePedidoDto> readAll(){
        return detallePedidoService.readAll();
    }
    @GetMapping("detallePedido/{id}")
    public Optional<DetallePedido> readById(@PathVariable Integer id){
        return detallePedidoService.readById(id);
    }
    @PostMapping("/detallePedido")
    public  DetallePedido create(@RequestBody DetallePedido detallePedido){
        return detallePedidoService.create(detallePedido);
    }
    @PutMapping("/detallePedido")
    public DetallePedido update(@RequestBody DetallePedido detallePedido){
        return detallePedidoService.update(detallePedido);
    }
    @PutMapping("/detallePedido/{id}")
    public String updateById(@PathVariable Integer id, @RequestBody DetallePedido detallePedido){
        return detallePedidoService.updateById(id, detallePedido);
    }
    @DeleteMapping("/detallePedido")
    public String delete(@RequestParam Integer id){
        return detallePedidoService.delete(id);
    }
    @GetMapping("/detallePedidoPrecio")
    public List<DetallePedido> buscarCantidadPedido(){
        return detallePedidoService.findAllByOrderByPrecioUnitarioDesc();
    }
    @GetMapping("/detallePedidoActivo")
    public List<DetallePedido> buscarActivo(){
        return detallePedidoService.findByActivoTrue();
    }
}
