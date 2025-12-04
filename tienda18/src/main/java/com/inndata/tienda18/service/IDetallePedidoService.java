package com.inndata.tienda18.service;

import com.inndata.tienda18.entity.DetallePedido;
import com.inndata.tienda18.model.request.DetallePedidoRequest;
import com.inndata.tienda18.model.response.DetallePedidoResponse;
import com.inndata.tienda18.model.response.Message;

import java.util.List;
import java.util.Optional;

public interface IDetallePedidoService {
    public List<DetallePedidoResponse> readAll();
    public Optional<DetallePedidoResponse> readById(Integer id);
    public Message create(DetallePedidoRequest detallePedidoRequest);
    public Message update(Integer id, DetallePedidoRequest detallePedidoRequest);
    public String updateById(Integer id, DetallePedido detallePedido);
    public Message delete(Integer id);
    //METODOS PERSONALIZADOS
    public List<DetallePedido>findAllByOrderByPrecioUnitarioDesc();
    public List<DetallePedido>findByActivoTrue();
}
