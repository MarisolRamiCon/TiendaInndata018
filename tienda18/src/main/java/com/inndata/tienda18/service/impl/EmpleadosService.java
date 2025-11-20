package com.inndata.tienda18.service.impl;




import com.inndata.tienda18.entity.Empleados;
import com.inndata.tienda18.repository.IEmpleadosRepository;
import com.inndata.tienda18.service.IEmpleadosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadosService implements IEmpleadosService {
    @Autowired
    //INYECCION DE DEPENDENCIA
    IEmpleadosRepository empleadosRepository;

    @Override
    public List<Empleados> readAll() {
        return empleadosRepository.findAll();
    }

    @Override
    public Optional<Empleados> readById(Integer id) {
        return empleadosRepository.findById(id);
    }

    @Override
    public Empleados create(Empleados empleados) {
        return empleadosRepository.save(empleados);
    }

    @Override
    public Empleados update(Empleados empleados) {
        return empleadosRepository.save(empleados);
    }

    @Override
    public String updateById(Integer id, Empleados empleados) {
        Optional<Empleados> empleados1= empleadosRepository.findById(id);

        if(empleados1.isPresent()){
            Empleados empleadosModificado= empleados1.get();
            //Modificar solo los atributos que se deben modificar, m2 y precio
            try {
                empleadosModificado.setNombre(empleados.getNombre());
                empleadosModificado.setApellido(empleados.getApellido());
                empleadosModificado.setPuesto(empleados.getPuesto());
                empleadosModificado.setSalario(empleados.getSalario());
                empleadosModificado.setFechacontratacion(empleados.getFechacontratacion());
                empleadosRepository.save(empleadosModificado);
                return ("Empleado actualizado");
            } catch (Exception e) {
                return "Tienes que modificar todos los campos excepto el id";
            }
        }else{
            return "No esta ese empleado";
        }
    }

    @Override
    public String delete(Integer id) {
        Optional<Empleados> empleados= empleadosRepository.findById(id);
        if(empleados.isPresent()) {
            Empleados empleados1= empleados.get();
            empleados1.setActivo(false);
            empleadosRepository.save(empleados1);
            return "El Empleado ha sido borrado";
        }else{
            return "No esta ese empleado";
        }
    }






    @Override
    public List<Empleados> findBySalario(Double salario) {
        return empleadosRepository.findBySalarioGreaterThan(salario);
    }

    @Override
    public List<Empleados> findByNombreApellido(String nombre, String apellido) {
            return empleadosRepository.findByNombreAndApellido(nombre, apellido);
    }


}
