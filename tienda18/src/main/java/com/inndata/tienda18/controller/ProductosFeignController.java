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

import com.inndata.tienda18.model.Productos;
import com.inndata.tienda18.service.impl.ProductosFeignService;

@RestController
@RequestMapping("/api/v1")
public class ProductosFeignController {

    @Autowired
    ProductosFeignService productosFeignService;

    @GetMapping("/Productos")
    public List<Productos> readAll() {
        return productosFeignService.readAll();
    }

    @GetMapping("/Productos/{id}")
    public Optional<Productos> readById(@PathVariable("id") Integer id){
        return productosFeignService.readById(id);
    }

    @PostMapping("/Productos")
    public Productos create(@RequestBody Productos producto){
        return productosFeignService.create(producto);
    }

    @PutMapping("/Productos/{id}")
    public String updateById(@PathVariable Integer id, @RequestBody Productos producto){
        return productosFeignService.updateById(id,producto);
    }

    @DeleteMapping("/Productos/{id}")
    public String delete(@PathVariable("id") Integer id){
        return productosFeignService.delete(id);
    }
    
    

}
