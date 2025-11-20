package com.inndata.tienda18.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Proveedores {

    private Integer id;
    private String nombre;
    private String contacto;

    private String correoElectronico;
    private String telefono;
    
}