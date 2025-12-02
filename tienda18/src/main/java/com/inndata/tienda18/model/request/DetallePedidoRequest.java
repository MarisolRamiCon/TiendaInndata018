package com.inndata.tienda18.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DetallePedidoRequest {
    private Integer id;
    private Integer idPedido;
    private double cantidadPedido;
    private double precioUnitario;
    private Integer idProducto;
    private Boolean activo;
}
