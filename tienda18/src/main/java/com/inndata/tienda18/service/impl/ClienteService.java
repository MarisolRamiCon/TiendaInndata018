package com.inndata.tienda18.service.impl;

import com.inndata.tienda18.entity.Clientes;
import com.inndata.tienda18.mapper.ClienteMapper;
import com.inndata.tienda18.model.request.ClienteRequest;
import com.inndata.tienda18.model.response.ClienteResponse;
import com.inndata.tienda18.repository.IClientesRepository;
import com.inndata.tienda18.service.IClientesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ClienteService implements IClientesService {
    private final IClientesRepository clientesRepository;

    public ClienteService(IClientesRepository clientesRepository) {
        this.clientesRepository = clientesRepository;
    }

    @Override
    public List<ClienteResponse> readAll() {
        return clientesRepository.findAll()
                .stream()
                .map(ClienteMapper::toResponse)
                .toList();
    }

    @Override
    public ClienteResponse readById(Integer idCliente) {
        return clientesRepository.findById(idCliente)
                .map(ClienteMapper::toResponse)
                .orElse(null);
    }

    @Override
    public ClienteResponse create(ClienteRequest request) {
        Clientes cliente = ClienteMapper.toEntity(request);
        Clientes guardado = clientesRepository.save(cliente);
        return ClienteMapper.toResponse(guardado);
    }

    @Override
    public ClienteResponse update(Integer idCliente, ClienteRequest request) {
        try {
            Optional<Clientes> clienteExistente = clientesRepository.findById(idCliente);
            if (clienteExistente.isEmpty()) {
                return null;
            }
            Clientes cliente = clienteExistente.get();
            cliente.setNombre(request.getNombre());
            cliente.setApellido(request.getApellido());
            cliente.setDireccion(request.getDireccion());
            cliente.setCorreo(request.getCorreo());
            cliente.setTelefono(request.getTelefono());
            Clientes actualizado = clientesRepository.save(cliente);
            return ClienteMapper.toResponse(actualizado);

        } catch (Exception e) {
            return new ClienteResponse();
        }
    }


    @Override
    public ClienteResponse deleteById(Integer idCliente) {
        try {
            Optional<Clientes> clienteExistente = clientesRepository.findById(idCliente);

            if (clienteExistente.isEmpty()) {
                return null; // ⚠️ Opcional: se puede lanzar excepción personalizada
            }

            Clientes cliente = clienteExistente.get();
            cliente.setActivo(false);

            Clientes actualizado = clientesRepository.save(cliente);
            return ClienteMapper.toResponse(actualizado);

        } catch (Exception e) {
            log.error("Error al eliminar cliente con id {}: {}", idCliente, e.getMessage(), e);
            return new ClienteResponse();
        }
    }

    @Override
    public List<ClienteResponse> findByLetra(String nombre) {
        return clientesRepository.findByNombreStartingWith(nombre)
                .stream()
                .map(ClienteMapper::toResponse)
                .toList();
    }

    @Override
    public List<ClienteResponse> findClienteEliminado(Boolean activo) {
        return clientesRepository.findByActivoFalse()
                .stream()
                .map(ClienteMapper::toResponse)
                .toList();
    }

    @Override
    public List<ClienteResponse> telefonoForaneo(String prefix) {
        return clientesRepository.telefonoForaneo(prefix)
                .stream()
                .map(ClienteMapper::toResponse)
                .toList();
    }
}
