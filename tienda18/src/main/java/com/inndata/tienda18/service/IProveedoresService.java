package com.inndata.tienda18.service;

import java.util.List;



import com.inndata.tienda18.model.ProveedoresRequest;
import com.inndata.tienda18.model.ProveedoresResponse;
import com.inndata.tienda18.model.ProveedoresStringResponse;

public interface IProveedoresService {
    public List<ProveedoresResponse> readAll();
    public ProveedoresResponse readById(Integer id);
    public ProveedoresResponse create(ProveedoresRequest proveedorRequest);
    public ProveedoresResponse update(Integer id, ProveedoresRequest proveedorRequest);
    public ProveedoresStringResponse updateById(Integer id, ProveedoresRequest proveedorRequest);
    public ProveedoresStringResponse delete (Integer id);

    public List<ProveedoresResponse> findByNombreLike(String nombreProveedor);
    public List<ProveedoresResponse> findByNombreContacto(String nombreContacto);
}
