package com.inndata.tienda18.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inndata.tienda18.entity.Proveedores;
import com.inndata.tienda18.repository.ProveedoresRepository;
import com.inndata.tienda18.service.IProveedoresService;

@Service
public class ProveedoresService implements IProveedoresService{

    @Autowired  // Inyección de dependencias
    ProveedoresRepository proveedoresRepository;

    @Override
    public List<Proveedores> readAll() {
        return proveedoresRepository.findAll();
    }

    @Override
    public Optional<Proveedores> readById(Integer id) {
        return proveedoresRepository.findById(id);
    }


    //Modificar
    @Override
    public Proveedores create(Proveedores proveedor) {
        return proveedoresRepository.save(proveedor);
    }

    @Override
    public Proveedores update(Proveedores proveedor) {
        return proveedoresRepository.save(proveedor);
    }

    @Override
    public String updateById(Integer id, Proveedores proveedor) {
        Optional<Proveedores> proveedor1= proveedoresRepository.findById(id);

        if(proveedor1.isPresent()){
            Proveedores proveedorModificado = proveedor1.get();
            
            try {
                proveedorModificado.setNombreProveedor(proveedor.getNombreProveedor());
                proveedorModificado.setContactoProveedor(proveedor.getContactoProveedor());
                proveedorModificado.setCorreoElectronicoProveedor(proveedor.getCorreoElectronicoProveedor());
                proveedorModificado.setTelefonoProveedor(proveedor.getTelefonoProveedor());

                proveedoresRepository.save(proveedorModificado);
                return ("Proveedor actualizado");
            } catch (Exception e) {
                return "Tienes que modificar todos los campos excepto el id";
            }
        }else{
            return "No existe registro del Proveedor introducido.";
        }
    }
        

    @Override
    public String delete(Integer id) {
        Optional<Proveedores> proveedor = proveedoresRepository.findById(id);
        if(proveedor.isPresent()) {
            Proveedores proveedor1= proveedor.get();
            proveedor1.setActivo(false);
            proveedoresRepository.save(proveedor1);
            return "El proveedor ha sido eliminado";
        }else{
            return "No existe registro del Proveedor introducido.";
        }
    }
    
}
