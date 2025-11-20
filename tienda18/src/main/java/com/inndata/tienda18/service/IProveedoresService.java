package com.inndata.tienda18.service;

import java.util.List;
import java.util.Optional;


import com.inndata.tienda18.entity.Proveedores;

public interface IProveedoresService {
    public List<Proveedores> readAll();
    public Optional<Proveedores> readById(Integer id);
    public Proveedores create(Proveedores proveedor);
    public Proveedores update(Proveedores proveedor);
    public String updateById(Integer id, Proveedores proveedor);
    public String delete (Integer id);
}
