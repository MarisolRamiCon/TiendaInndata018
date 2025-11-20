package com.inndata.tienda18.service;

import com.inndata.tienda18.entity.Clientes;

import java.util.List;
import java.util.Optional;

public interface IClientesService {
    public List<Clientes> readAll();
    public Optional<Clientes> readById(Integer idCliente);
    public Clientes create(Clientes clientes);
    public Clientes update(Clientes clientes);
    public String updateById(Integer idCliente, Clientes clientes);
    public String deleteById(Integer idCliente);
    //Metodos personalizados por medio de palabras claves de JpaRepository
    //public List<Clientes> findByPrecio (Double precio);
    //public List<Clientes> findByM2Precio (Integer m2, Double precio);
    //public List<Clientes> m2AndPrecio(Integer m2,Double precio);
}
