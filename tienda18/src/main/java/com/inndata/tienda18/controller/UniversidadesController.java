package com.inndata.tienda18.controller;


import com.inndata.tienda18.model.Universidades;
import com.inndata.tienda18.service.impl.UniversidadesService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class UniversidadesController {
    @Autowired
    UniversidadesService universidadesService;
    @GetMapping("universidades")
    List<Universidades> readAll(){
        return universidadesService.readAll();
    }


    @GetMapping("/universidades/{id}")
    public Optional<Universidades> readById(@PathVariable Integer id){
        return universidadesService.readById(id);
    }

    // Cuando el metodo es para crear se usa POSTMAPPING
    @PostMapping("/universidades")
    public Universidades create(@RequestBody Universidades universidades){
        return universidadesService.create(universidades);
    }

    @PutMapping("/universidades")
    public Universidades update(@RequestBody Universidades universidades){
        return universidadesService.update(universidades);
    }

    @PutMapping("/universidades/{id}")
    public String updateById(@PathVariable Integer id, @RequestBody Universidades universidades){
        return universidadesService.updateById(id,universidades);
    }

    @DeleteMapping("/universidades/{id}")
    public String delete(@PathParam("id") Integer id){
        return universidadesService.delete(id);
    }



}