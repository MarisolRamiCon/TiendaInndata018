package com.inndata.tienda18.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "empleados")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Empleados {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id_empleados")
    private Integer idempleados;
    @Column(name="nombre")
    private String nombre;
    @Column(name="apellido")
    private String apellido;
    @Column(name="puesto")
    private String puesto;
    @Column(name="salario")
    private Double salario;
    @Column(name="fecha_contratacion")
    private String fechacontratacion;
    @Column(name="activo")
    private Boolean activo;
}
