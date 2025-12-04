package com.inndata.tienda18.service.impl;

import com.inndata.tienda18.entity.DetallePedido;
import com.inndata.tienda18.model.request.DetallePedidoRequest;
import com.inndata.tienda18.model.response.DetallePedidoResponse;
import com.inndata.tienda18.model.response.Message;
import com.inndata.tienda18.repository.DetallePedidoRepository;
import com.inndata.tienda18.service.IDetallePedidoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetallePedidoService implements IDetallePedidoService {
    private final DetallePedidoRepository detallePedidoRepository;

    public DetallePedidoService(DetallePedidoRepository detallePedidoRepository) {
        this.detallePedidoRepository = detallePedidoRepository;
    }
    @Override
    public List<DetallePedidoResponse> readAll() {
        return detallePedidoRepository.findAll()
                .stream()
                .filter(detalle -> Boolean.TRUE.equals(detalle.getActivo())) // FILTRO
                .map(detalle -> new DetallePedidoResponse(
                        detalle.getIdDetallePedido(),
                        detalle.getIdPedido(),
                        detalle.getCantidadPedido(),
                        detalle.getPrecioUnitario(),
                        detalle.getIdProducto(),
                        detalle.getActivo()
                ))
                .toList();
    }

    @Override
    public Optional<DetallePedidoResponse> readById(Integer id) {
        return detallePedidoRepository.findById(id).map(
                detallePedido -> new DetallePedidoResponse(
                        detallePedido.getIdDetallePedido(),
                        detallePedido.getIdPedido(),
                        detallePedido.getCantidadPedido(),
                        detallePedido.getPrecioUnitario(),
                        detallePedido.getIdProducto(),
                        detallePedido.getActivo()
                ));
    }

    @Override
    public Message create(DetallePedidoRequest detallePedidoRequest) {
        try {
            DetallePedido detallePedido = new DetallePedido();
            detallePedido.setIdDetallePedido(detallePedidoRequest.getId());
            detallePedido.setIdPedido(detallePedidoRequest.getIdPedido());
            detallePedido.setCantidadPedido(detallePedidoRequest.getCantidadPedido());
            detallePedido.setPrecioUnitario(detallePedidoRequest.getPrecioUnitario());
            detallePedido.setIdProducto(detallePedidoRequest.getIdProducto());
            detallePedido.setActivo(detallePedidoRequest.getActivo());

            detallePedidoRepository.save(detallePedido);

            return new Message("Detalle del pedido creado correctamente");

        } catch (Exception e) {

            return new Message("Error al crear el detalle del pedido: " + e.getMessage());
        }
    }


    @Override
    public Message update(Integer id,DetallePedidoRequest detallePedidoRequest) {
       Optional<DetallePedido> detallePedido = detallePedidoRepository.findById(id);
       DetallePedido detallePedido1 ;
       if (detallePedido.isPresent()){
           try {
               detallePedido1 = detallePedido.get();
               detallePedido1.setIdPedido(detallePedidoRequest.getIdPedido());
               detallePedido1.setCantidadPedido(detallePedidoRequest.getCantidadPedido());
               detallePedido1.setPrecioUnitario(detallePedidoRequest.getPrecioUnitario());
               detallePedido1.setIdProducto(detallePedidoRequest.getIdProducto());
               detallePedido1.setActivo(detallePedidoRequest.getActivo());
               return new Message("Detalle del pedido actualizado");
           }catch (Exception e){
                return new Message("Error al actualizar el detalle del pedido: " + e.getMessage());
           }
       }else {
           return new Message("No se encuentran esos detalles del pedido");
       }
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
    public Message delete(Integer id) {
        Optional<DetallePedido> detallePedido = detallePedidoRepository.findById(id);
        if (detallePedido.isPresent()){
            DetallePedido detallePedido1 = detallePedido.get();
            detallePedido1.setActivo(false);
            detallePedidoRepository.save(detallePedido1);
            return new Message("Los detalles del pedido han sido borrados");
        }else {
            return new Message("No existen esos detalles");
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
