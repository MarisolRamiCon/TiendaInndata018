package com.inndata.tienda18.controller;

import com.inndata.tienda18.entity.Clientes;
import com.inndata.tienda18.service.impl.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ClienteController {
    @Autowired
    ClienteService clienteService;
    @GetMapping("/clientes")
    public List<Clientes> readAll(){
        return clienteService.readAll();
    }

}
