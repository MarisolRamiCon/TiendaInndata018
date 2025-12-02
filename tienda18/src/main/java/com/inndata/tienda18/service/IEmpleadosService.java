package com.inndata.tienda18.service;

import com.inndata.tienda18.entity.Empleados;
import com.inndata.tienda18.model.EmpleadosResponse;

import java.util.List;
import java.util.Optional;

public interface IEmpleadosService {
    public List<Empleados> readAll();
    public Optional<Empleados> readById(Integer id);
    //METODOS CRUD C>CREATE, R>READ. U> UPDATE, D> DELETE
    public String create(Empleados empleados);
    public String update(Empleados empleados);
    public String updateById(Integer id, Empleados empleados);
    public String delete(Integer id);
    public List<EmpleadosResponse> findBySalario(Double salario);
    public List<Empleados> findByNombreApellido(String nombre, String apellido);
    public List<Empleados> NombreandApellido(String nombre, String apellido);

}
