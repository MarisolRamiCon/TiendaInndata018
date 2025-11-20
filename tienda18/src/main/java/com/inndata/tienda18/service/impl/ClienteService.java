package com.inndata.tienda18.service.impl;

import com.inndata.tienda18.entity.Clientes;
import com.inndata.tienda18.repository.IClientesRepository;
import com.inndata.tienda18.service.IClientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService implements IClientesService {
    @Autowired
    IClientesRepository clientesRepository;
    @Override
    public List<Clientes> readAll() {
        return clientesRepository.findAll();
    }
    @Override
    public Optional<Clientes> readById(Integer idCliente) {
        return clientesRepository.findById(idCliente);
    }

    @Override
    public Clientes create(Clientes clientes) {
        return clientesRepository.save(clientes);
    }

    @Override
    public Clientes update(Clientes clientes) {
        return clientesRepository.save(clientes);
    }

    @Override
    public String updateById(Integer idCliente, Clientes clientes) {
        return "";
    }

    @Override
    public String deleteById(Integer idCliente) {
        return "";
    }
}
