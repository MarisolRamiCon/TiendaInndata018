package com.inndata.tienda18.service;

import java.util.List;
import java.util.Optional;

import com.inndata.tienda18.model.ProductosRequest;
import com.inndata.tienda18.model.ProductosResponse;
import com.inndata.tienda18.model.ProductosStringRequest;
import com.inndata.tienda18.model.ProductosStringResponse;

public interface IProductosService {
    public List<ProductosRequest> readAll();
    public Optional<ProductosRequest> readById(Integer id);
    public ProductosResponse create(ProductosRequest producto);
    public ProductosResponse update(ProductosRequest producto);
    public ProductosStringResponse updateById(Integer id, ProductosRequest producto);
    public ProductosStringResponse delete (Integer id);


    public List<ProductosResponse> findByNombre(ProductosStringRequest productoRequest);
    
}
