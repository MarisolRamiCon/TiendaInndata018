package com.inndata.tienda18.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadosResponse {


    private Integer idempleados;
    private String nombre;
    private String apellido;
    private String puesto;
    private Double salario;
}
