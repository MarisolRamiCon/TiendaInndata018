package com.inndata.tienda18.service;

import java.util.List;

import com.inndata.tienda18.model.ProductosRequest;
import com.inndata.tienda18.model.ProductosResponse;
import com.inndata.tienda18.model.ProductosStringResponse;

public interface IProductosService {
    public List<ProductosResponse> readAll();
    public ProductosResponse readById(Integer id);
    public ProductosResponse create(ProductosRequest productoRequest);
    public ProductosResponse update(Integer id, ProductosRequest productoRequest);
    public ProductosStringResponse updateById(Integer id, ProductosRequest productoRequest);
    public ProductosStringResponse delete (Integer id);


    public List<ProductosResponse> findByNombreLike(String nombreProducto);
    public List<ProductosResponse> productosPrecioMayorQue(Double precio);
    
}
