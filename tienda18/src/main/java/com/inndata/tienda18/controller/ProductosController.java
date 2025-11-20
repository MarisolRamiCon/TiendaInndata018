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
import com.inndata.tienda18.model.ProductosResponse;
import com.inndata.tienda18.service.impl.ProductosService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/v1")
public class ProductosController {
    @Autowired
    ProductosService productosService;

    @GetMapping("/productos")
    public List<ProductosRequest> readAll(){
        return productosService.readAll();
    }
    
    @GetMapping("/productos/{id}")
    public Optional<ProductosRequest> readById(@PathVariable Integer id){
        return productosService.readById(id);
    }

    @PostMapping("/producto")
    public ProductosResponse create(@RequestBody ProductosRequest productoRequest){
        return productosService.create(productoRequest);
    }

    @PutMapping("/producto")
    public ProductosResponse update(@RequestBody ProductosRequest productoRequest){
        return productosService.update(productoRequest);
    }

    @PutMapping("/producto/{id}")
    public ProductosStringResponse updateById(@PathVariable Integer id, @RequestBody ProductosRequest productoRequest){
        return productosService.updateById(id,productoRequest);
    }

    @DeleteMapping("/producto")
    public ProductosStringResponse delete(@PathParam("id") Integer id){
        return productosService.delete(id);
    }
    
}
