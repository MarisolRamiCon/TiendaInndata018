package com.inndata.tienda18.service.impl;

import com.inndata.tienda18.feign.IUniEmpleados;
import com.inndata.tienda18.model.Universidades;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UniversidadesService implements IUniEmpleados {
    @Autowired
    IUniEmpleados iuniempleados;
    @Override
    public List<Universidades> readAll() {
        return iuniempleados.readAll();
    }

    @Override
    public Optional<Universidades> readById(Integer id) {
        return iuniempleados.readById(id);
    }

    @Override
    public Universidades create(Universidades universidades) {
        return iuniempleados.create(universidades);

    }

    @Override
    public Universidades update(Universidades universidades) {
        return iuniempleados.update(universidades);
    }

    @Override
    public String updateById(Integer id, Universidades universidades) {
        Optional<Universidades> universidades1= iuniempleados.readById(id);

        if(universidades1.isPresent()){

            Universidades universidadesModificado= universidades1.get();
            //Modificar solo los atributos que se deben modificar, m2 y precio
            try {
                universidadesModificado.setNombre(universidades.getNombre());
                universidadesModificado.setDireccion(universidades.getDireccion());
                universidadesModificado.setEmail(universidades.getEmail());
                universidadesModificado.setCodigopostal(universidades.getCodigopostal());

                iuniempleados.updateById(id,universidadesModificado);
                return ("Universidad actualizada");
            } catch (Exception e) {
                return "Tienes que modificar todos los campos excepto el id";
            }
        }else{
            return "No esta esa universidad";
        }

    }

    @Override
    public String delete(Integer id) {
        Optional<Universidades> universidades= iuniempleados.readById(id);
        if(universidades.isPresent()) {
            Universidades universidades1= universidades.get();
            iuniempleados.delete(universidades1.getId());
            return "La universidad ha sido borrada";
        }else{
            return "No hay tal universidad";
        }
    }


}