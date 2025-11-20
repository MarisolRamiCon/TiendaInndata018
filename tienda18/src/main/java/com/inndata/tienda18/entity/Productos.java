package com.inndata.tienda18.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data


@Entity
@Table(name = "productos")
public class Productos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer idProducto;
    @Column(name = "nombre")
    private String nombreProducto;
    @Column (name = "descripcion")
    private String descripcionProducto;
    @Column (name = "precio")
    private Double precioProducto;
    @Column (name = "categoria")
    private String categoriaProducto;
    @Column (name = "stock")
    private Integer stockProducto;
    @Column (name = "activo")
    private Boolean activo;

    @ManyToOne
    @JoinColumn (name = "id_proovedor")
    private Proveedores idProveedor;

}
