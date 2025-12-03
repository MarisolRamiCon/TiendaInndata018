package com.inndata.tienda18.service.impl;

import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;

import com.inndata.tienda18.feign.ProveedoresClient;
import com.inndata.tienda18.model.Proveedores;

@Service
public class ProveedoresFeignService implements ProveedoresClient {
    
    private final ProveedoresClient proveedoresClient;

    public ProveedoresFeignService(ProveedoresClient proveedoresClient) {
        this.proveedoresClient = proveedoresClient;
    }

    @Override
    public List<Proveedores> readAll() {
        return proveedoresClient.readAll();
    }

    @Override
    public Optional<Proveedores> readById(Integer id) {
        return proveedoresClient.readById(id);
    }

    @Override
    public Proveedores create(Proveedores proveedor) {
        return proveedoresClient.create(proveedor);
    }

    @Override
    public String updateById(Integer id, Proveedores proveedor) {
        return proveedoresClient.updateById(id, proveedor);
    }

    @Override
    public String delete(Integer id) {
        return proveedoresClient.delete(id);
    }

}
