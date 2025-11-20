package com.inndata.tienda18.service.impl;

import com.inndata.tienda18.entity.DetallePedido;
import com.inndata.tienda18.model.DetallePedidoDto;
import com.inndata.tienda18.repository.DetallePedidoRepository;
import com.inndata.tienda18.service.IDetallePedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetallePedidoService implements IDetallePedidoService {
    @Autowired
    DetallePedidoRepository detallePedidoRepository;
    @Override
    public List<DetallePedidoDto> readAll() {
        List<DetallePedido> listadeDetalles=detallePedidoRepository.findAll();
        List<DetallePedidoDto> listaFinal= listadeDetalles.stream().map(
                detalle -> {
                    DetallePedidoDto detalleDto = new DetallePedidoDto(detalle.getIdDetallePedido(),detalle.getCantidadPedido(),detalle.getPrecioUnitario(),
                            detalle.getIdProducto(),detalle.getActivo());
                    return detalleDto;
                }
        ).toList();
        return listaFinal;
    }

    @Override
    public Optional<DetallePedido> readById(Integer id) {
        return detallePedidoRepository.findById(id);
    }

    @Override
    public DetallePedido create(DetallePedido detallePedido) {
        return detallePedidoRepository.save(detallePedido);
    }

    @Override
    public DetallePedido update(DetallePedido detallePedido) {
        return detallePedidoRepository.save(detallePedido);
    }

    @Override
    public String updateById(Integer id, DetallePedido detallePedido) {
       Optional<DetallePedido> detallePedido1 = detallePedidoRepository.findById(id);
       if (detallePedido1.isPresent()){
           DetallePedido detalleModificado = detallePedido1.get();
           try {
               detalleModificado.setIdPedido(detallePedido.getIdPedido());
               detalleModificado.setIdProducto(detallePedido.getIdProducto());
               detalleModificado.setCantidadPedido(detallePedido.getCantidadPedido());
               detalleModificado.setPrecioUnitario(detallePedido.getPrecioUnitario());
               detallePedidoRepository.save(detalleModificado);
               return "Detalle pedido actualizado";
           }catch (Exception e){
               return "Tienes que modificar todos los campos excepto el id";
           }
       }else {
           return "No se encuentran esos detalles del pedido";
       }
    }

    @Override
    public String delete(Integer id) {
        Optional<DetallePedido> detallePedido = detallePedidoRepository.findById(id);
        if (detallePedido.isPresent()){
            DetallePedido detallePedido1 = detallePedido.get();
            detallePedido1.setActivo(false);
            detallePedidoRepository.save(detallePedido1);
            return "Los detalles del pedido han sido borrados";
        }else {
            return "No existen esos detalles";
        }
    }
    //METODOS PERZONALIZADOS
    @Override
    public List<DetallePedido> findAllByOrderByPrecioUnitarioDesc() {
        return detallePedidoRepository.findAllByOrderByPrecioUnitarioDesc();
    }

    @Override
    public List<DetallePedido> findByActivoTrue() {
        return detallePedidoRepository.findByActivoTrue();
    }


}
