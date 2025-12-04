package com.inndata.tienda18.mapper;

import com.inndata.tienda18.entity.Clientes;
import com.inndata.tienda18.model.request.ClienteRequest;
import com.inndata.tienda18.model.response.ClienteResponse;

public class ClienteMapper {
    // Constructor privado evita instanciación, Solucion por observacion de sonarqube
    private ClienteMapper() {
        throw new UnsupportedOperationException("Utility class should not be instantiated");
    }

    public static Clientes toEntity(ClienteRequest request) {
        Clientes cliente = new Clientes();
        cliente.setNombre(request.getNombre());
        cliente.setApellido(request.getApellido());
        cliente.setDireccion(request.getDireccion());
        cliente.setCorreo(request.getCorreo());
        cliente.setTelefono(request.getTelefono());
        cliente.setActivo(true);
        return cliente;
    }

    public static ClienteResponse toResponse(Clientes cliente) {
        ClienteResponse response = new ClienteResponse();
        response.setIdCliente(cliente.getIdCliente());
        response.setNombre(cliente.getNombre());
        response.setApellido(cliente.getApellido());
        response.setDireccion(cliente.getDireccion());
        response.setCorreo(cliente.getCorreo());
        response.setTelefono(cliente.getTelefono());
        response.setActivo(cliente.isActivo());
        return response;
    }
}

