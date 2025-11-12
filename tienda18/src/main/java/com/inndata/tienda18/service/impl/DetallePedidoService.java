package com.inndata.tienda18.service.impl;

import com.inndata.tienda18.entity.DetallePedido;
import com.inndata.tienda18.repository.DetallePedidoRepository;
import com.inndata.tienda18.service.IDetallePedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DetallePedidoService implements IDetallePedidoService {
    @Autowired
    DetallePedidoRepository detallePedidoRepository;
    @Override
    public List<DetallePedido> readAll() {
        return detallePedidoRepository.findAll();
    }
}
