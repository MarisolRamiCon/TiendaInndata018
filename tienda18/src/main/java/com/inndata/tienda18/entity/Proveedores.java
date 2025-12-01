package com.inndata.tienda18.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
    private Integer idProveedor; // INT UNSIGNED -> Integer

    @Column(name = "nombre")
    private String nombreProveedor;

    @Column(name = "contacto")
    private String contactoProveedor;

    @Column(name = "correo_electronico")
    private String correoElectronicoProveedor;

    @Column(name = "telefono")
    private String telefonoProveedor;

    /* Relación inversa opcional: un proveedor tiene muchos productos.
       MappedBy hace referencia al nombre del atributo en Producto: "proveedor". */
    @OneToMany(mappedBy = "proveedor", fetch = FetchType.LAZY)
    private List<Productos> productos = new ArrayList<>();

    @Column(name = "activo")
    private Boolean activo = true;


}