package com.inndata.tienda18.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
    private Integer idProducto; // INT UNSIGNED -> Integer

    @Column(name= "nombre")
    private String nombreProducto;

    @Column(name = "descripcion")
    private String descripcionProducto;

    @Column(name = "precio")
    private Double precioProducto;

    @Column(name = "categoria")
    private String categoriaProducto = "General";

    @Column(name = "stock")
    private Integer stockProducto = 0;

    @Column(name = "activo")
    private Boolean activo = true;

    /* Relación ManyToOne con Proveedor. 
       optional = true porque proveedor_id puede ser NULL (ON DELETE SET NULL). */
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "proveedor_id", referencedColumnName = "id")
    private Proveedores proveedor;

}
