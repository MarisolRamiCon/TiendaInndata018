package com.inndata.tienda18.service;

import com.inndata.tienda18.entity.Pedido;
import com.inndata.tienda18.model.request.PedidoRequest;
import com.inndata.tienda18.model.response.PedidoResponse;
import com.inndata.tienda18.model.response.Message;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IPedidosService {
    public List<PedidoResponse> readAll();
    public Optional<PedidoResponse> readById(Integer id);
    public Message create(PedidoRequest pedidoRequest);
    public Message update(Integer id, PedidoRequest pedidoRequest);
    public String updateById(Integer id, Pedido pedido);
    public Message delete(Integer id);
    //METODOS PERSONALIZADOS
    public  List<Pedido> findByFechaPedidoBetween(LocalDate inicio, LocalDate fin);
    public List<Pedido> finByTotalPedidoGreaterThan(Double total);//mayor que
}
