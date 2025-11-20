package com.inndata.tienda18.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ProductosResponse {
    private Integer id;
    private String nombreProducto;
    private String descripcionProducto;
    private Double precioProducto;
    private String categoriaProducto;
    private Integer stockProducto;
    private Integer idProveedor;

}
