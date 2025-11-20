package com.inndata.tienda18.controller;

import com.inndata.tienda18.entity.Pedido;
import com.inndata.tienda18.model.PedidoDto;
import com.inndata.tienda18.service.impl.PedidoService;
import jakarta.persistence.criteria.CriteriaBuilder;
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
    public List<PedidoDto> readAll(){
        return pedidoService.readAll();
    }
    @GetMapping("/pedido/{id}")
    public Optional<Pedido> readById(@PathVariable Integer id){
        return pedidoService.readById(id);
    }
    @PostMapping("/pedido")
    public Pedido create(@RequestBody Pedido pedido){
        return pedidoService.create(pedido);
    }
    @PutMapping("/pedido")
    public Pedido update(@RequestBody Pedido pedido){
        return pedidoService.update(pedido);
    }
    @PutMapping("/pedido/{id}")
    public String updateById(@PathVariable Integer id, @RequestBody Pedido pedido){
        return pedidoService.updateById(id,pedido);
    }
    @DeleteMapping("/pedido")
    public String delete(@RequestParam Integer id){
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
