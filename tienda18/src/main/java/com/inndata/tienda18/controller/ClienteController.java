package com.inndata.tienda18.controller;

import com.inndata.tienda18.entity.Clientes;
import com.inndata.tienda18.service.impl.ClienteService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ClienteController {
    @Autowired
    ClienteService clienteService;
    @GetMapping("/clientes")
    public List<Clientes> readAll(){
        return clienteService.readAll();
    }
    @GetMapping("/clientes/{id}")
    public Optional<Clientes> readById(@PathVariable() Integer idCliente){
        return clienteService.readById(idCliente);
    }
    @PostMapping("/clientes")
    //@RequestBody es para obtener el formato JSon
    public Clientes create(@RequestBody Clientes clientes){
        return clienteService.create(clientes);
    }
    @PutMapping("/clientes")
    public Clientes update(@RequestBody Clientes clientes){
        return clienteService.update(clientes);
    }
    @PutMapping("/clientes/{id}")
    public String updateById(@PathVariable Integer id, @RequestBody Clientes clientes){
        return clienteService.updateById(id,clientes);
    }
    @DeleteMapping("/clientes")
    public String delete(@PathParam("idCliente") Integer idCliente){
        return clienteService.deleteById(idCliente);
    }
}
