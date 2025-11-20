package com.inndata.tienda18.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inndata.tienda18.model.ProductosRequest;

public interface ProductosRepository extends JpaRepository<ProductosRequest, Integer> {

    public List<ProductosRequest> findByNombreLike(String nombre);
    

}
