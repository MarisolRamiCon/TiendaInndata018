package com.inndata.tienda18.service.impl;

import com.inndata.tienda18.entity.Pedido;
import com.inndata.tienda18.model.request.PedidoRequest;
import com.inndata.tienda18.model.response.Message;
import com.inndata.tienda18.model.response.PedidoResponse;
import com.inndata.tienda18.repository.PedidoRepository;
import com.inndata.tienda18.service.IPedidosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService implements IPedidosService {
    @Autowired //INYECCION DE DEPENDENCIA
    PedidoRepository pedidoRepository;
    @Override
    public List<PedidoResponse> readAll() {
        return pedidoRepository.findAll()
                .stream()
                .filter(pedido -> Boolean.TRUE.equals(pedido.getActivo()))
                .map(pedido ->
                        new PedidoResponse(
                                pedido.getId(),
                                pedido.getIdCliente(),
                                pedido.getFechaPedido(),
                                pedido.getTotalPedido(),
                                pedido.getActivo()
                        )
                ).toList();
    }


    @Override
    public Optional<PedidoResponse> readById(Integer id) {
        return pedidoRepository.findById(id).map(
                pedidoEntity -> new PedidoResponse(
                        pedidoEntity.getId(),
                        pedidoEntity.getIdCliente(),
                        pedidoEntity.getFechaPedido(),
                        pedidoEntity.getTotalPedido(),
                        pedidoEntity.getActivo()
                )
        );
    }

    @Override
    public Message create(PedidoRequest pedidoRequest) {
        try {
            Pedido pedido = new Pedido();
            pedido.setIdCliente(pedidoRequest.getIdCliente());
            pedido.setFechaPedido(pedidoRequest.getFechaPedido());
            pedido.setTotalPedido(pedidoRequest.getTotalPedido());
            pedido.setActivo(true);

            pedidoRepository.save(pedido);

            return new Message("Pedido creado correctamente");
        } catch (Exception e) {
            return new Message("Error al crear el pedido: " + e.getMessage());
        }
    }

    @Override
    public Message update(Integer id, PedidoRequest pedidoRequest) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        if (pedido.isPresent()) {
            Pedido pedidoModificado = pedido.get();
            try {
                pedidoModificado.setIdCliente(pedidoRequest.getIdCliente());
                pedidoModificado.setFechaPedido(pedidoRequest.getFechaPedido());
                pedidoModificado.setTotalPedido(pedidoRequest.getTotalPedido());
                pedidoRepository.save(pedidoModificado);
                return new Message("Pedido actualizado correctamente");
            } catch (Exception e) {
                return new Message("Debes enviar todos los campos correctos");
            }
        } else {
            return new Message("No se encuentra el pedido");
        }
    }


    @Override
    public String updateById(Integer id, Pedido pedido) {
        Optional<Pedido> pedido1 = pedidoRepository.findById(id);
        if (pedido1.isPresent()){
            Pedido pedidoModificado = pedido1.get();
            try {
                pedidoModificado.setIdCliente(pedido.getIdCliente());
                pedidoModificado.setFechaPedido(pedido.getFechaPedido());
                pedidoModificado.setTotalPedido(pedido.getTotalPedido());
                pedidoRepository.save(pedidoModificado);
                return "Departamento actualizado";
            }catch (Exception e){
                return "Tienes que modificar todos los campos excepto el id";
            }
        }else{
            return "No se encuentra el pedido";
        }
    }

    @Override
    public Message delete(Integer id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);

        if (pedido.isPresent()) {
            try {
                Pedido pedido1 = pedido.get();
                pedido1.setActivo(false);
                pedidoRepository.save(pedido1);
                return new Message("El pedido ha sido eliminado ");
            } catch (Exception e) {
                return new Message("Error al borrar el pedido: " + e.getMessage());
            }
        } else {
            return new Message("No existe el pedido");
        }
    }


    @Override
    public List<Pedido> findByFechaPedidoBetween(LocalDate inicio, LocalDate fin) {
        return pedidoRepository.findByFechaPedidoBetween(inicio,fin);
    }

    @Override
    public List<Pedido> finByTotalPedidoGreaterThan(Double total) {
        return pedidoRepository.findByTotalPedidoGreaterThan(total);
    }

}
