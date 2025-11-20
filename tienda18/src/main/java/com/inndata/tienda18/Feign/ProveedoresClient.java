package com.inndata.tienda18.Feign;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "Proveedores", url = "https://691696fba7a34288a27dc06d.mockapi.io/api/v1")
public interface ProveedoresClient {
    @GetMapping("/Proveedores")
    public List<Proveedores> readAll();

    @GetMapping("/Proveedores/{id}")
    public Optional<Proveedores> readById(@PathVariable("id") Integer id);

    @PostMapping("/Proveedores")
    public Proveedores create(@RequestBody Proveedores proveedor);

    @PutMapping("/Proveedores/{id}")
    public String updateById(@PathVariable Integer id, @RequestBody Proveedores proveedor);

    @DeleteMapping("/Proveedores/{id}")
    public String delete(@PathVariable("id") Integer id);
}