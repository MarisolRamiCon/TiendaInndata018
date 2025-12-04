package com.inndata.tienda18.model;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ProveedoresResponse {
    private Integer id;
    private String nombre;
    private String correoElectronicoProveedor;
    private String contacto;

public ProveedoresResponse(Integer id, String nombre, String correoElectronicoProveedor) {
        this.id = id;
        this.nombre = nombre;
        this.correoElectronicoProveedor = correoElectronicoProveedor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProveedoresResponse that = (ProveedoresResponse) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(nombre, that.nombre) &&
            Objects.equals(correoElectronicoProveedor, that.correoElectronicoProveedor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, correoElectronicoProveedor);
    }
}


