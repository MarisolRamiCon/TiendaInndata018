package com.inndata.tienda18.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PedidoRequest {
    private Integer idCliente;
    private LocalDate fechaPedido;
    private Double totalPedido;
}

