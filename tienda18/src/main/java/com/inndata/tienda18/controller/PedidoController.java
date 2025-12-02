package com.inndata.tienda18.controller;

import com.inndata.tienda18.entity.Pedido;
import com.inndata.tienda18.model.request.PedidoRequest;
import com.inndata.tienda18.model.response.Message;
import com.inndata.tienda18.model.response.PedidoResponse;
import com.inndata.tienda18.service.impl.PedidoService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1")
public class PedidoController {
    @Autowired
    PedidoService pedidoService;
    @GetMapping("/pedido")
    public List<PedidoResponse> readAll(){
        return pedidoService.readAll();
    }
    @GetMapping("/pedido/{id}")
    public Optional<PedidoResponse> readById(@PathVariable Integer id){
        return pedidoService.readById(id);
    }
    @PostMapping("/pedido")
    public Message create(@RequestBody PedidoRequest pedidoRequest){
        return pedidoService.create(pedidoRequest);
    }
    @PutMapping("/pedido")
    public Message update(@PathParam("id")Integer id, @RequestBody PedidoRequest pedidoRequest){
        return pedidoService.update(id, pedidoRequest);
    }
    @PutMapping("/pedido/{id}")
    public String updateById(@PathVariable Integer id, @RequestBody Pedido pedido){
        return pedidoService.updateById(id,pedido);
    }
    @DeleteMapping("/pedido")
    public Message delete(@RequestParam Integer id){
        return pedidoService.delete(id);
    }
    //METODOS PERSONALIZADOS
    @GetMapping("/pedidoFechas")
    public List findByFechaPedidoBetween(@RequestParam LocalDate inicio, @RequestParam LocalDate fin){
        return pedidoService.findByFechaPedidoBetween(inicio,fin);
    }
    @GetMapping("/pedidoMayor")
    public List findByTotalPedidoGreaterThan(@RequestParam Double total){
        return pedidoService.finByTotalPedidoGreaterThan(total);
    }
}
