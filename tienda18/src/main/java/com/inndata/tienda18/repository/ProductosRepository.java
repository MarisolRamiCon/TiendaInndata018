package com.inndata.tienda18.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.inndata.tienda18.entity.Productos;

public interface ProductosRepository extends JpaRepository<Productos, Integer> {

    List<Productos> findByNombreProductoContainingIgnoreCase(String nombreProducto);

    @Query(value = "select * from productos where  precio >:precio and activo=true", nativeQuery = true)
    public List<Productos> productosPrecioMayorQue(Double precio);
    

}
