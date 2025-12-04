package com.inndata.tienda18.model;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ProductosResponse {
    
    private Integer id;
    private String nombre;
    private String descripcionProducto;
    private Double precioProducto;
    private String categoriaProducto;

    @Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ProductosResponse that = (ProductosResponse) o;
    return Objects.equals(id, that.id) &&
        Objects.equals(nombre, that.nombre) &&
        Objects.equals(descripcionProducto, that.descripcionProducto) &&
        Objects.equals(precioProducto, that.precioProducto) &&
        Objects.equals(categoriaProducto, that.categoriaProducto);
}

@Override
public int hashCode() {
    return Objects.hash(id, nombre, descripcionProducto, precioProducto, categoriaProducto);
}

}
