package com.inndata.tienda18.service.impl;




import com.inndata.tienda18.entity.Empleados;
import com.inndata.tienda18.model.EmpleadosResponse;
import com.inndata.tienda18.repository.IEmpleadosRepository;
import com.inndata.tienda18.service.IEmpleadosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

@Service
public class EmpleadosService implements IEmpleadosService {
    @Autowired
    //INYECCION DE DEPENDENCIA
    IEmpleadosRepository empleadosRepository;

    @Override
    public List<Empleados> readAll() {
       try{ return empleadosRepository.findAll().stream().filter(
               empleados -> empleados.getActivo().equals(true)).toList();
    } catch (NullPointerException e){
           return List.of();
       }
    }

    @Override
    public Optional<Empleados> readById(Integer id) {
        try {
            return empleadosRepository.findById(id).filter(
                    empleados -> empleados.getActivo().equals(true));

        } catch (NullPointerException e) {
            return Optional.empty();
        }
    }
    @Override
    public String create(Empleados empleados) {
        try{ empleadosRepository.save(empleados);
            return "empleado guardado correctamente";
        }  catch (InputMismatchException e){
            return "Ingresa los datos correctamente";
        }
    }

    @Override
    public String update(Empleados empleados) {
        try{ empleadosRepository.save(empleados);
            return "empleado guardado correctamente";
    }  catch (InputMismatchException e){
          return "Ingresa los datos correctamente";
      }
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
    public List<EmpleadosResponse> findBySalario(Double salario) {
       // return empleadosRepository.findBySalarioGreaterThan(salario);
        List<Empleados> listaDeEmpleados=empleadosRepository.findBySalarioGreaterThan(salario);
        List<EmpleadosResponse> listaDeEmpleadosResponse= listaDeEmpleados.stream().map(
                empleados -> EntityToResponse(empleados)
        ).toList();
        return listaDeEmpleadosResponse;
     //   return listaDeEmpleados.stream().map(this::EntityToResponse).toList();
    }
private EmpleadosResponse EntityToResponse(Empleados empleado){
   EmpleadosResponse empleadosResponse = new EmpleadosResponse();
        empleadosResponse.setIdempleados(empleado.getIdempleados());
        empleadosResponse.setNombre(empleado.getNombre());
        empleadosResponse.setApellido(empleado.getApellido());
        empleadosResponse.setPuesto(empleado.getPuesto());
        empleadosResponse.setSalario(empleado.getSalario());
        return empleadosResponse;

        /*  return new EmpleadosResponse(
             empleado.getIdempleados(),
             empleado.getNombre(),
             empleado.getApellido(),
             empleado.getPuesto(),
             empleado.getSalario()
     );*/
    }
    @Override
    public List<Empleados> findByNombreApellido(String nombre, String apellido) {
            return empleadosRepository.findByNombreAndApellido(nombre, apellido);
    }

    @Override
    public List<Empleados> NombreandApellido(String nombre, String apellido) {
        return empleadosRepository.NombreAndApellido(nombre,apellido);
    }



}
