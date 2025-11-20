package com.inndata.tienda18.service.impl;

import com.inndata.tienda18.entity.Clientes;
import com.inndata.tienda18.repository.IClientesRepository;
import com.inndata.tienda18.service.IClientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClienteService implements IClientesService {
    @Autowired
    IClientesRepository clientesRepository;
    @Override
    public List<Clientes> readAll() {
        return clientesRepository.findAll();
    }
}
