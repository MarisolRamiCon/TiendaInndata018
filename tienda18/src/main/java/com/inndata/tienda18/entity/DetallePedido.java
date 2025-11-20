package com.inndata.tienda18.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "detalles_pedido")
public class DetallePedido {
    @Id

    @Column(name = "id_detalle_pedido")
    private Integer idDetallePedido;
    @Column(name = "id_pedido")
    private Integer idPedido;
    @Column(name = "id_producto")
    private Integer idProducto;
    @Column(name = "cantidad_pedido")
    private Double cantidadPedido;
    @Column(name = "precio_unitario")
    private Double precioUnitario;
    @Column(name = "activo")
    private Boolean activo;
}
