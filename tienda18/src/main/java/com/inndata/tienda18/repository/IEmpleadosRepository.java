package com.inndata.tienda18.repository;

import com.inndata.tienda18.entity.Empleados;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IEmpleadosRepository extends JpaRepository<Empleados, Integer> {
    //Metodos personalizados con palabras claves de Jpa
    public List<Empleados> findBySalarioGreaterThan(Double salario);
    //buscar los empleados con nombre y apellidos
    public List<Empleados> findByNombreAndApellido(String nombre, String apellido);
}
