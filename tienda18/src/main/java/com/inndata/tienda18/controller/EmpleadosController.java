package com.inndata.tienda18.controller;

import com.inndata.tienda18.entity.Empleados;
import com.inndata.tienda18.model.EmpleadosResponse;
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
    @PostMapping("/empleados")
    public String create(@RequestBody Empleados empleados){
        return empleadosService.create(empleados);
    }

    @PutMapping("/empleados")
    public String update(@RequestBody Empleados empleados){
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
    public List<EmpleadosResponse> findBySalario(@PathParam("salario") Double salario){
        return empleadosService.findBySalario(salario);
    }

    @GetMapping("/empleadosSalarioPuesto")
    public List<Empleados> findByNombreAndApellido(
            @PathParam("nombre") String nombre, @PathParam("apellido") String apellido){
                  return empleadosService.findByNombreApellido(nombre,apellido);
    }

    @GetMapping("/empleados/nombreandapellido")
    public List<Empleados> NombreandApellido(
            @PathParam("nombre") String nombre,@PathParam("apellido") String apellido) {
                return empleadosService.NombreandApellido(nombre, apellido);
    }


    }