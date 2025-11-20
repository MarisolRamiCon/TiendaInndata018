package com.inndata.tienda18.controller;

import com.inndata.tienda18.entity.Empleados;
import com.inndata.tienda18.service.impl.EmpleadosService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class EmpleadosController {
    @Autowired
    EmpleadosService empleadosService;
    //METODOS CRUD C>CREATE, R>READ. U> UPDATE, D> DELETE
    //GETMAPPING POSTMAPPING PUTMAPPING DELETEMAPPING
    @GetMapping("/empleados")
    public List<Empleados> readAll(){
        return empleadosService.readAll();
    }
    @GetMapping("/empleados/{id}")
    public Optional<Empleados> readById(@PathVariable Integer id){
        return empleadosService.readById(id);
    }

    // Cuando el metodo es para crear se usa POSTMAPPING
    @PostMapping("/empleado")
    public Empleados create(@RequestBody Empleados empleados){
        return empleadosService.create(empleados);
    }

    @PutMapping("/empleados")
    public Empleados update(@RequestBody Empleados empleados){
        return empleadosService.update(empleados);
    }

    @PutMapping("/empleados/{id}")
    public String updateById(@PathVariable Integer id, @RequestBody Empleados empleados){
        return empleadosService.updateById(id,empleados);
    }

    @DeleteMapping("/empleados")
    public String delete(@PathParam("id") Integer id){
        return empleadosService.delete(id);
    }

    @GetMapping("/empleadosSalario")
    public List<Empleados> findByPrecio(@PathParam("salario") Double salario){
        return empleadosService.findBySalario(salario);
    }

    @GetMapping("/empleadosSalarioPuesto")
    public List<Empleados> findBySalarioPuesto(@PathParam("salario") Double salario, @PathParam("puesto") String puesto){
        return empleadosService.findBySalarioPuesto(salario,puesto);
    }


    }