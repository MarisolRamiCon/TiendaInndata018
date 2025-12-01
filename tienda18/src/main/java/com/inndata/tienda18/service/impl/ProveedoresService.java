package com.inndata.tienda18.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;


import com.inndata.tienda18.entity.Proveedores;
import com.inndata.tienda18.model.ProveedoresRequest;
import com.inndata.tienda18.model.ProveedoresResponse;
import com.inndata.tienda18.model.ProveedoresStringResponse;
import com.inndata.tienda18.repository.ProveedoresRepository;
import com.inndata.tienda18.service.IProveedoresService;

@Service
public class ProveedoresService implements IProveedoresService {

    private final ProveedoresRepository proveedoresRepository;

    public ProveedoresService(ProveedoresRepository proveedoresRepository) {
        this.proveedoresRepository = proveedoresRepository;
    }

    @Override
    public List<ProveedoresResponse> readAll() {
        return proveedoresRepository.findAll()
        .stream()
        .filter(x -> x.getActivo().equals(true))
        .map(proveedor -> new ProveedoresResponse(
            proveedor.getIdProveedor(),
            proveedor.getNombreProveedor(),
            proveedor.getCorreoElectronicoProveedor()
            ))
            .toList();
    }

    @Override
    public ProveedoresResponse readById(Integer id) {
        Optional<Proveedores> proveedor = proveedoresRepository.findById(id);
        
        if (proveedor.isPresent()){
            Proveedores proveedorAEncontrar = proveedor.get();
            return new ProveedoresResponse(id, proveedorAEncontrar.getNombreProveedor(),proveedorAEncontrar.getCorreoElectronicoProveedor());
        } else {
            return new ProveedoresResponse();
        }
    }

    @Override
    public ProveedoresResponse create(ProveedoresRequest proveedorRequest) {
        Proveedores proveedor = new Proveedores();
        Optional<Proveedores> proveedorSearch = proveedoresRepository.findById(proveedorRequest.getId());
        if (!proveedorSearch.isPresent()){

            proveedor.setContactoProveedor(proveedorRequest.getContactoProveedor());
            proveedor.setNombreProveedor(proveedorRequest.getNombre());
            proveedor.setCorreoElectronicoProveedor(proveedorRequest.getCorreoElectronicoProveedor());
            proveedor.setTelefonoProveedor(proveedorRequest.getTelefonoProveedor());
            proveedoresRepository.save(proveedor);
            return new ProveedoresResponse(proveedor.getIdProveedor(), proveedor.getNombreProveedor(), proveedor.getCorreoElectronicoProveedor());
        } else {
            return new ProveedoresResponse();
        }

    }

    @Override
    public ProveedoresResponse update(Integer id, ProveedoresRequest proveedorRequest){
        Optional<Proveedores> proveedor = proveedoresRepository.findById(id);
        Proveedores proveedorAModificar = new Proveedores();
        if (proveedor.isPresent()) {
            proveedorAModificar.setIdProveedor(proveedorRequest.getId());
            proveedorAModificar.setNombreProveedor(proveedorRequest.getNombre());
            proveedorAModificar.setContactoProveedor(proveedorRequest.getContactoProveedor());
            proveedorAModificar.setCorreoElectronicoProveedor(proveedorRequest.getCorreoElectronicoProveedor());
            proveedorAModificar.setTelefonoProveedor(proveedorRequest.getTelefonoProveedor());

            proveedoresRepository.save(proveedorAModificar);

            return new ProveedoresResponse(proveedorRequest.getId(), proveedorAModificar.getNombreProveedor(),proveedorAModificar.getCorreoElectronicoProveedor());

        } else {
            return new ProveedoresResponse();
        }   
    }

    @Override
    public ProveedoresStringResponse updateById(Integer id, ProveedoresRequest proveedorRequest) {
        Optional<Proveedores> proveedor = proveedoresRepository.findById(id);
        Proveedores proveedorAModificar;
        if (proveedor.isPresent()){
            proveedorAModificar = proveedor.get();
            
            proveedorAModificar.setNombreProveedor(proveedorRequest.getNombre());
            proveedorAModificar.setContactoProveedor(proveedorRequest.getContactoProveedor());
            proveedorAModificar.setCorreoElectronicoProveedor(proveedorRequest.getCorreoElectronicoProveedor());
            proveedorAModificar.setTelefonoProveedor(proveedorRequest.getTelefonoProveedor());
            proveedoresRepository.save(proveedorAModificar);

            return new ProveedoresStringResponse("Proveedor actualizado exitosamente.");

        } else {
            return new ProveedoresStringResponse("Proveedor no encontrado.");
        }

        
    }

    @Override
    public ProveedoresStringResponse delete(Integer id) {
        Optional<Proveedores> proveedorAEliminar = proveedoresRepository.findById(id);
        if(proveedorAEliminar.isPresent()){
            Proveedores proveedor = proveedorAEliminar.get();
            proveedor.setActivo(false);
            proveedoresRepository.save(proveedor);
            return new ProveedoresStringResponse("Proveedor eliminado exitosamente.");
        } else {
            return new ProveedoresStringResponse("No se encuentra el proveedor.");
            
        }
    }

    @Override
    public List<ProveedoresResponse> findByNombreLike(String nombreProveedor) {
        if (nombreProveedor == null || nombreProveedor.isBlank()) {
        throw new IllegalArgumentException("El parámetro 'nombre' es requerido.");
    }

    return proveedoresRepository.findByNombreProveedorContainingIgnoreCase(nombreProveedor)
        .stream()
        .map(proveedor -> new ProveedoresResponse(
            proveedor.getIdProveedor(),
            proveedor.getNombreProveedor(),
            proveedor.getCorreoElectronicoProveedor()
        ))
        .toList();
    }

    @Override
    public List<ProveedoresResponse> findByNombreContacto(String nombreContacto) {
        if (nombreContacto == null || nombreContacto.isBlank()) {
        throw new IllegalArgumentException("El parámetro 'nombre' es requerido.");
    }

    return proveedoresRepository.proveedoresActivosPorContacto(nombreContacto)
        .stream()
        .map(proveedor -> new ProveedoresResponse(
            proveedor.getIdProveedor(),
            proveedor.getNombreProveedor(),
            proveedor.getCorreoElectronicoProveedor(),
            proveedor.getContactoProveedor()
        ))
        .toList();
    }
    

}

