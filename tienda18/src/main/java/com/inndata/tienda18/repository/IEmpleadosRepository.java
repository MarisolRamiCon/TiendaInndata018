package com.inndata.tienda18.repository;

import com.inndata.tienda18.entity.Empleados;
import com.inndata.tienda18.model.EmpleadosResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IEmpleadosRepository extends JpaRepository<Empleados, Integer> {
    //Metodos personalizados con palabras claves de Jpa
    public List<Empleados> findBySalarioGreaterThan(Double salario);
    //buscar los empleados con nombre y apellidos
    public List<Empleados> findByNombreAndApellido(String nombre, String apellido);
    //Metodos personalizados por medio de Query
    @Query(value = "select * from empleados where nombre = :nombre and apellido = :apellido", nativeQuery = true)
    public List<Empleados> NombreAndApellido(String nombre, String apellido);
}
