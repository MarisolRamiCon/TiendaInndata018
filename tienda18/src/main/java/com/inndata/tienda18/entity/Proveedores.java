package com.inndata.tienda18.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data


@Entity
@Table(name = "proveedores")
public class Proveedores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer idProveedor;
    @Column(name = "nombre")
    private String nombreProveedor;
    @Column (name = "contacto")
    private String contactoProveedor;
    @Column (name = "correo_electronico")
    private Double correoElectronicoProveedor;
    @Column (name = "telefono")
    private String telefonoProveedor;
    @Column (name = "activo")
    private Boolean activo;
}