package com.inndata.tienda18.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ProveedoresRequest {

    private Integer id;
    private String nombre;
    private String contactoProveedor;
    private String correoElectronicoProveedor;
    private String telefonoProveedor;
}
