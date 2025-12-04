package com.inndata.tienda18.controller;

import java.util.List;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.inndata.tienda18.model.ProveedoresRequest;
import com.inndata.tienda18.model.ProveedoresResponse;
import com.inndata.tienda18.model.ProveedoresStringResponse;
import com.inndata.tienda18.service.impl.ProveedoresService;

import jakarta.websocket.server.PathParam;


@RestController
@RequestMapping("/api/v1")
public class ProveedoresController {
    private final ProveedoresService proveedoresService;

    public ProveedoresController(ProveedoresService proveedoresService) {
        this.proveedoresService = proveedoresService;
    }

    @GetMapping("/proveedores")
    public List<ProveedoresResponse> readAll(){
        return proveedoresService.readAll();
    }
    @GetMapping("/proveedores/{id}")
    public ProveedoresResponse readById(@PathVariable Integer id){
        return proveedoresService.readById(id);
    }

    
    @PostMapping("/proveedor")
    public ProveedoresResponse create(@RequestBody ProveedoresRequest proveedorRequest){
        return proveedoresService.create(proveedorRequest);
    }

    @PutMapping("/proveedor")
    public ProveedoresResponse update(@PathParam("id") Integer id, @RequestBody ProveedoresRequest proveedorRequest){
        return proveedoresService.update(id, proveedorRequest);
    }

    @PutMapping("/proveedor/{id}")
    public ProveedoresStringResponse updateById(@PathVariable Integer id, @RequestBody ProveedoresRequest proveedorRequest){
        return proveedoresService.updateById(id, proveedorRequest);
    }

    @DeleteMapping("/proveedor")
    public ProveedoresStringResponse delete(@PathParam("id") Integer id){
        return proveedoresService.delete(id);
    }

    @GetMapping("/proveedorNombreLike/{nombreProveedor}")
    public List<ProveedoresResponse> findByNombreLike(@PathVariable String nombreProveedor){
        return proveedoresService.findByNombreLike(nombreProveedor);
    }

    @GetMapping("/proveedoresActivosPorNombreContacto/{contacto}")
    public List<ProveedoresResponse> productosPrecioMayorQue(@PathVariable String contacto){
        return proveedoresService.findByNombreContacto(contacto);
    
    }
}
