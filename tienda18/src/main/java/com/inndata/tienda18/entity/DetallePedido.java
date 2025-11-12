package com.inndata.tienda18.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/*@NoArgsConstructor
@AllArgsConstructor
@Data*/
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

    public DetallePedido() {
    }

    public DetallePedido(Integer idDetallePedido, Integer idPedido, Integer idProducto, Double cantidadPedido, Double precioUnitario) {
        this.idDetallePedido = idDetallePedido;
        this.idPedido = idPedido;
        this.idProducto = idProducto;
        this.cantidadPedido = cantidadPedido;
        this.precioUnitario = precioUnitario;
    }

    public Integer getIdDetallePedido() {
        return idDetallePedido;
    }

    public void setIdDetallePedido(Integer idDetallePedido) {
        this.idDetallePedido = idDetallePedido;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Double getCantidadPedido() {
        return cantidadPedido;
    }

    public void setCantidadPedido(Double cantidadPedido) {
        this.cantidadPedido = cantidadPedido;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
}
