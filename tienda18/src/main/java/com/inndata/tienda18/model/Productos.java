package com.inndata.tienda18.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Productos {

    private Integer id;
    private String nombre;
    private String descripcion;
    private Double precio;

    private String categoria;

    private Integer idProveedor;
    private Integer stock;

}