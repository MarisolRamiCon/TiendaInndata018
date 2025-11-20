package com.inndata.tienda18.service;

import com.inndata.tienda18.entity.DetallePedido;
import com.inndata.tienda18.model.DetallePedidoDto;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;
import java.util.Optional;

public interface IDetallePedidoService {
    public List<DetallePedidoDto> readAll();
    public Optional<DetallePedido> readById(Integer id);
    public DetallePedido create(DetallePedido detallePedido);
    public DetallePedido update( DetallePedido detallePedido);
    public String updateById(Integer id, DetallePedido detallePedido);
    public String delete(Integer id);
    //METODOS PERSONALIZADOS
    public List<DetallePedido>findAllByOrderByPrecioUnitarioDesc();
    public List<DetallePedido>findByActivoTrue();
}
