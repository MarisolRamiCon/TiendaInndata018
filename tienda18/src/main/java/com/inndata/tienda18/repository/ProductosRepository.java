package com.inndata.tienda18.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inndata.tienda18.entity.Productos;

public interface ProductosRepository extends JpaRepository<Productos, Integer> {

    public List<Productos> findByNombreLike(String nombre);
    

}
