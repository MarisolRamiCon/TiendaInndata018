package com.inndata.tienda18.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.inndata.tienda18.entity.Proveedores;

public interface ProveedoresRepository extends JpaRepository<Proveedores, Integer> {

    List<Proveedores> findByNombreProveedorContainingIgnoreCase(String nombreProveedor);

    @Query(value = "select * from proveedores where LOWER(contacto) LIKE LOWER(CONCAT('%', :contacto, '%')) and activo=true", nativeQuery = true)
    public List<Proveedores> proveedoresActivosPorContacto(String contacto);

}
