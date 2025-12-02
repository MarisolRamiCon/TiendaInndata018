package com.inndata.tienda18.repository;

import com.inndata.tienda18.entity.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IClientesRepository extends JpaRepository<Clientes, Integer> {
    //Metodos personalizados con palabras claves de Jpa
    //Buscar clientes que su nombre empiece con la letra ""
    public List<Clientes> findByNombreStartingWith(String nombre);

    //findByActiveFalse() buscar clientes inactivos
    public List<Clientes> findByActivoFalse();

    //Metodos personalizados por medio de Query
    @Query(value = "SELECT * FROM clientes WHERE SUBSTR(telefono, 1, 2) = :prefix", nativeQuery = true)
    List<Clientes> telefonoForaneo(@Param("prefix") String prefix);
}
