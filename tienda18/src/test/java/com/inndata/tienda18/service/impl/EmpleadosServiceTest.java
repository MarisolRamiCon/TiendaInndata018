package com.inndata.tienda18.service.impl;



import com.inndata.tienda18.entity.Empleados;
import com.inndata.tienda18.model.EmpleadosResponse;
import com.inndata.tienda18.repository.IEmpleadosRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class EmpleadosServiceTest {

    Empleados d1= new Empleados("Juan","Lopez","Limpieza",12000,"1999-06-22",true);
    Empleados d2= new Empleados("Maria","bucio","Limpieza",12000,"1999-08-29",true);
    Empleados d3= new Empleados("Pedro","Cervantez","Limpieza",12000,"2000-06-01",true);

    @InjectMocks
    EmpleadosService empleadosService;

    @Mock
    IEmpleadosRepository empleadosRepository;

    @BeforeEach
    void setUp() {
        System.out.println("se esta ejecutando before");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Se esta ejecutando after");
    }

 /*   @Test
    void suma() {
        int num1= empleadosService.suma(9,5);
        //resultado esperado 14
        assertEquals(14,num1);
    }*/

    @Test
    void updateById() {
        Mockito.when(empleadosRepository.findById(1)).thenReturn(Optional.of(d1));
        d1.setNombre("Pedro Luis");
        d1.setApellido("Alvarez");
        Mockito.when(empleadosRepository.save(d1)).thenReturn(d1);
        assertEquals("Empleado actualizado",empleadosService.updateById(1,d1));
    }

    @Test
    void UpdateByIdElse() {
        Empleados d4= new Empleados();
        Mockito.when(empleadosRepository.findById(4)).thenReturn(Optional.empty());
        assertEquals("No esta ese empleado", empleadosService.updateById(4,d4));
    }


    @Test
    void delete() {
        Mockito.when(empleadosRepository.findById(1)).thenReturn(Optional.of(d1));
        //  Mockito.verify(d1);
        d1.setActivo(false);
        Mockito.when(empleadosRepository.save(d1)).thenReturn(d1);
        assertEquals("El departamento ha sido borrado",empleadosService.delete(1));

        //   Mockito.verify(d3).setActivo(false);

    }

    @Test
    void deleteelse() {
        Empleados d5= new Empleados();
        Mockito.when(empleadosRepository.findById(5)).thenReturn(Optional.empty());
        assertEquals("El Empleado ha sido borrado", empleadosService.delete(5));
    }
   /* // Asegura que no se llame al metodo dentro del else
        Mockito.verify(miMock, Mockito.never()).metodoDentroDelElse();*/



}
