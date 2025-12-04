package com.inndata.tienda18.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DetallePedidoResponse {
    private Integer id;
    private Integer idPedido;
    private double cantidadPedido;
    private double precioUnitario;
    private Integer idProducto;
    private Boolean activo;
}
