package com.inndata.tienda18.service;

import com.inndata.tienda18.model.request.ClienteRequest;
import com.inndata.tienda18.model.response.ClienteResponse;

import java.util.List;

public interface IClientesService {
    public List<ClienteResponse> readAll();

    public ClienteResponse readById(Integer idCliente);

    public ClienteResponse create(ClienteRequest request);

    public ClienteResponse update(Integer idCliente, ClienteRequest request);

    public ClienteResponse deleteById(Integer idCliente);

    //Metodos personalizados por medio de palabras claves de JpaRepository
    public List<ClienteResponse> findByLetra(String nombre);

    public List<ClienteResponse> findClienteEliminado(Boolean activo);

    public List<ClienteResponse> telefonoForaneo(String telefono);
}
