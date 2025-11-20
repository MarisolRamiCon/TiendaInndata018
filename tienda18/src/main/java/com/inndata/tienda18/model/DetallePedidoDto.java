package com.inndata.tienda18.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DetallePedidoDto {
    private Integer id;
    private double cantidadPedido;
    private double precioUnitario;
    private Integer idProducto;
    private Boolean activo;
}
