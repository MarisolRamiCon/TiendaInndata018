package com.inndata.tienda18.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PedidoResponse {
    private Integer id;
    private Integer idCliente;
    private LocalDate fechaPedido;
    private Double totalPedido;
    private Boolean activo;
}
