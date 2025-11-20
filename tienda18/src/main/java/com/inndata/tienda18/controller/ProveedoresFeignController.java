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

import com.inndata.tienda18.model.Proveedores;
import com.inndata.tienda18.service.impl.ProveedoresFeignService;


@RestController
@RequestMapping("/api/v1")

public class ProveedoresFeignController {

    @Autowired
    ProveedoresFeignService proveedoresFeignService;

    @GetMapping("/Proveedores")
    public List<Proveedores> readAll() {
        return proveedoresFeignService.readAll();
    }

    @GetMapping("/Proveedores/{id}")
    public Optional<Proveedores> readById(@PathVariable("id") Integer id){
        return proveedoresFeignService.readById(id);
    }

    @PostMapping("/Proveedores")
    public Proveedores create(@RequestBody Proveedores proveedor){
        return proveedoresFeignService.create(proveedor);
    }

    @PutMapping("/Proveedores/{id}")
    public String updateById(@PathVariable Integer id, @RequestBody Proveedores proveedor){
        return proveedoresFeignService.updateById(id,proveedor);
    }

    @DeleteMapping("/Proveedores/{id}")
    public String delete(@PathVariable("id") Integer id){
        return proveedoresFeignService.delete(id);
    }

}
