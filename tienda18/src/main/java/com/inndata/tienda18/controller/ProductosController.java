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

import com.inndata.tienda18.entity.Productos;
import com.inndata.tienda18.model.ProductosDto;
import com.inndata.tienda18.service.impl.ProductosService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/v1")
public class ProductosController {
    @Autowired
    ProductosService productosService;

    @GetMapping("/productos")
    public List<ProductosDto> readAll(){
        return productosService.readAll();
    }
    
    @GetMapping("/productos/{id}")
    public Optional<ProductosDto> readById(@PathVariable Integer id){
        return productosService.readById(id);
    }

    
    @PostMapping("/producto")
    public Productos create(@RequestBody Productos producto){
        return productosService.create(producto);
    }

    @PutMapping("/producto")
    public Productos update(@RequestBody Productos producto){
        return productosService.update(producto);
    }

    @PutMapping("/producto/{id}")
    public String updateById(@PathVariable Integer id, @RequestBody Productos producto){
        return productosService.updateById(id,producto);
    }

    @DeleteMapping("/producto")
    public String delete(@PathParam("id") Integer id){
        return productosService.delete(id);
    }
    
}
