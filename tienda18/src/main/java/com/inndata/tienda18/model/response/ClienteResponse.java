package com.inndata.tienda18.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClienteResponse {
    private Integer idCliente;
    private String nombre;
    private String apellido;
    private String direccion;
    private String correo;
    private String telefono;
    private boolean activo;
}
