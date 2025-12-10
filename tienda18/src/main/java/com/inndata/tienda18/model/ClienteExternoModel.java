package com.inndata.tienda18.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClienteExternoModel {

    private Integer idCteExt;
    private String nombreExt;
    private String apellidoExt;
    private String direccionExt;
    private String correoExt;
    private String telefonoExt;
    private Boolean activoExt;
}
