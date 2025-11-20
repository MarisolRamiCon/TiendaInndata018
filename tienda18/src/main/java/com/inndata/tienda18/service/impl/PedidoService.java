package com.inndata.tienda18.service.impl;

import com.inndata.tienda18.entity.Pedido;
import com.inndata.tienda18.model.PedidoDto;
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
    public List<PedidoDto> readAll() {
        List<Pedido> listaDePedidos=pedidoRepository.findAll();
        List<PedidoDto> listaFinal=listaDePedidos.stream().map(
                pedido -> {
                    PedidoDto pedidoDto= new PedidoDto(pedido.getId(),pedido.getFechaPedido(),pedido.getTotalPedido(),pedido.getActivo());
                    return pedidoDto;
                }
        ).toList();
        return listaFinal;
    }

    @Override
    public Optional<Pedido> readById(Integer id) {
        return pedidoRepository.findById(id);
    }

    @Override
    public Pedido create(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @Override
    public Pedido update(Pedido pedido) {
        return pedidoRepository.save(pedido);
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
    public String delete(Integer id) {
        Optional<Pedido> pedido=pedidoRepository.findById(id);
        if (pedido.isPresent()){
            Pedido pedido1 = pedido.get();
            pedido1.setActivo(false);
            pedidoRepository.save(pedido1);
            return "El pedido ha sido borrado";
        }else {
            return "No existe el pedido";
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
