package com.inndata.tienda18.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inndata.tienda18.feign.ProductosClient;
import com.inndata.tienda18.model.Productos;

@Service
public class ProductosFeignService implements ProductosClient {

    private final ProductosClient productosClient;

    public ProductosFeignService(ProductosClient productosClient) {
        this.productosClient = productosClient;
    }

    @Override
    public List<Productos> readAll() {
        return productosClient.readAll();
    }

    @Override
    public Optional<Productos> readById(Integer id) {
        return productosClient.readById(id);
    }

    @Override
    public Productos create(Productos producto) {
        return productosClient.create(producto);
    }

    @Override
    public String updateById(Integer id, Productos producto) {
        return productosClient.updateById(id, producto);
    }

    @Override
    public String delete(Integer id) {
        return productosClient.delete(id);
    }




}
