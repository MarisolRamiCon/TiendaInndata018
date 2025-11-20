package com.inndata.tienda18.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inndata.tienda18.entity.Proveedores;
import com.inndata.tienda18.service.impl.ProveedoresService;

import jakarta.websocket.server.PathParam;


@RestController
@RequestMapping("/api/v1")
public class ProveedoresController {
    @Autowired
    ProveedoresService proveedoresService;

    @GetMapping("/proveedores")
    public List<Proveedores> readAll(){
        return proveedoresService.readAll();
    }
    @GetMapping("/proveedores/{id}")
    public Optional<Proveedores> readById(@PathVariable Integer id){
        return proveedoresService.readById(id);
    }

    
    @PostMapping("/proveedor")
    public Proveedores create(@RequestBody Proveedores proveedor){
        return proveedoresService.create(proveedor);
    }

    @PutMapping("/proveedor")
    public Proveedores update(@RequestBody Proveedores proveedor){
        return proveedoresService.update(proveedor);
    }

    @PutMapping("/proveedor/{id}")
    public String updateById(@PathVariable Integer id, @RequestBody Proveedores proveedor){
        return proveedoresService.updateById(id,proveedor);
    }

    @DeleteMapping("/proveedor")
    public String delete(@PathParam("id") Integer id){
        return proveedoresService.delete(id);
    }
    
}
