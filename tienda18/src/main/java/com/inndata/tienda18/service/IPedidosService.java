package com.inndata.tienda18.service;

import com.inndata.tienda18.entity.Pedido;
import com.inndata.tienda18.model.PedidoDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IPedidosService {
    public List<PedidoDto> readAll();
    public Optional<Pedido> readById(Integer id);
    public Pedido create(Pedido pedido);
    public Pedido update(Pedido pedido);
    public String updateById(Integer id, Pedido pedido);
    public String delete(Integer id);
    //METODOS PERSONALIZADOS
    public  List<Pedido> findByFechaPedidoBetween(LocalDate inicio, LocalDate fin);
    public List<Pedido> finByTotalPedidoGreaterThan(Double total);//mayor que
}
