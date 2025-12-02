package com.inndata.tienda18.feign;


import com.inndata.tienda18.model.Universidades;
import jakarta.websocket.server.PathParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "universidades", url = "https://6916930ea7a34288a27db5e7.mockapi.io/api/v1")
public interface IUniEmpleados {
    //Interface Entidad Service
    @GetMapping("/universidades")
    public List<Universidades> readAll();

    @GetMapping("/universidades/{id}")
    public Optional<Universidades> readById(@PathVariable Integer id);

    // Cuando el metodo es para crear se usa POSTMAPPING
    @PostMapping("/universidades")
    public Universidades create(@RequestBody Universidades universidades);

    @PutMapping("/universidades")
    public Universidades update(@RequestBody Universidades universidades);

    @PutMapping("/universidades/{id}")
    public String updateById(@PathVariable Integer id, @RequestBody Universidades universidades);

    @DeleteMapping("/universidades/{id}")
    public String delete(@PathParam("id") Integer id);


}