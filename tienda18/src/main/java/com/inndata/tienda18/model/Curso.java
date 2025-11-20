package com.inndata.tienda18.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Curso {
    private Integer id;
    private String nombreCurso;
    private String descripcion;
    private Integer creditos;
    private Integer duracionHoras;
}
