package com.inndata.tienda18.service.impl;

import com.inndata.tienda18.feign.ClienteExternoFeign;
import com.inndata.tienda18.model.ClienteExternoModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteExternoService implements ClienteExternoFeign {
    private final ClienteExternoFeign clienteExternoFeign;

    public ClienteExternoService(ClienteExternoFeign clienteExternoFeign) {
        this.clienteExternoFeign = clienteExternoFeign;
    }

    @Override
    public List<ClienteExternoModel> readAll() {
        return clienteExternoFeign.readAll();
    }

    @Override
    public Optional<ClienteExternoModel> readById(Integer idCteExt) {
        return clienteExternoFeign.readById(idCteExt);
    }

    @Override
    public ClienteExternoModel create(ClienteExternoModel clienteExternoModel) {
        return clienteExternoFeign.create(clienteExternoModel);
    }

    @Override
    public ClienteExternoModel updateById(Integer idCteExt, ClienteExternoModel clienteExternoModel) {
        Optional<ClienteExternoModel> clienteExternoModel1 = clienteExternoFeign.readById(idCteExt);
        if (clienteExternoModel1.isPresent()) {
            ClienteExternoModel clienteActualizado = clienteExternoModel1.get();
            try {
                clienteActualizado.setNombreExt(clienteExternoModel.getNombreExt());
                clienteActualizado.setApellidoExt(clienteExternoModel.getApellidoExt());
                clienteActualizado.setDireccionExt(clienteExternoModel.getDireccionExt());
                clienteActualizado.setCorreoExt(clienteExternoModel.getCorreoExt());
                clienteActualizado.setTelefonoExt(clienteExternoModel.getTelefonoExt());
                return clienteExternoFeign.updateById(idCteExt, clienteActualizado);
            } catch (Exception e) {
                return new ClienteExternoModel();
            }
        } else {
            return new ClienteExternoModel();
        }
    }

    @Override
    public String deleteById(Integer idCteExt) {
        Optional<ClienteExternoModel> clienteExistente = clienteExternoFeign.readById(idCteExt);
        if (clienteExistente.isPresent()) {
            try {
                ClienteExternoModel clienteEliminado = clienteExistente.get();
                clienteEliminado.setActivoExt(false);
                clienteExternoFeign.updateById(clienteEliminado.getIdCteExt(), clienteEliminado);
                return "El cliente ha sido eliminado";
            } catch (Exception e) {
                return "Error al eliminar el cliente";
            }
        } else {
            return "No existe ese cliente";
        }
    }
}
