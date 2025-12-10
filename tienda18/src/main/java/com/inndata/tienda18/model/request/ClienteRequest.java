package com.inndata.tienda18.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClienteRequest {
    private String nombre;
    private String apellido;
    private String direccion;
    private String correo;
    private String telefono;
}
