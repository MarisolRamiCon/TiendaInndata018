package com.inndata.tienda18.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PedidoDto {
    private Integer id;
    private LocalDate fechaPedido;
    private Double totalPedido;
    private Boolean activo;
}
