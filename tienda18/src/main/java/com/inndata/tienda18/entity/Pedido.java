package com.inndata.tienda18.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Integer id;
    @Column(name = "id_cliente")
    private Integer idCliente;
    @Column(name = "fecha_pedido")
    private LocalDate fechaPedido;
    @Column(name = "total_pedido")
    private Double totalPedido;
    @Column(name = "activo")
    private Boolean activo;
}
