package com.inndata.tienda18.service.impl;

import com.inndata.tienda18.feign.CursoClient;
import com.inndata.tienda18.model.Curso;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class CursoServiceTest {

    @InjectMocks
    CursoService cursoService;

    @Mock
    CursoClient cursoClient;

    @BeforeEach
    void setUp() {
        System.out.println("Se ejecuta el before");
    }
    @AfterEach
    void tearDown() {
        System.out.println("Se ejecuta el after");
    }
    @Test
    void ReadAll() {
        List<Curso> lista = List.of(new Curso(1, "Matemáticas","Algebra",4,15), new Curso(2, "Programación","Java",5,20));

        Mockito.when(cursoClient.readAll()).thenReturn(lista);

        List<Curso> resultado = cursoService.readAll();

        assertEquals(lista, resultado);
        verify(cursoClient, times(1)).readAll();
    }
    @Test
    void ReadById() {
        Curso curso = new Curso(1, "Matemáticas","Algebra",4,15);
        Mockito.when(cursoClient.readById(1)).thenReturn(Optional.of(curso));

        Optional<Curso> resultado = cursoService.readById(1);

        assertTrue(resultado.isPresent());
        assertEquals(curso, resultado.get());
    }

    @Test
    void ReadByIdElse() {
        Mockito.when(cursoClient.readById(99)).thenReturn(Optional.empty());

        Optional<Curso> curso = cursoService.readById(99);

        assertTrue(curso.isEmpty());
    }
    @Test
    void Create() {
        Curso curso = new Curso(null, "Nuevo curso","Descripcion",3,10);
        Curso curso1 = new Curso(1, "Phyton","Orientado a datos",3,10);

        Mockito.when(cursoClient.create(curso)).thenReturn(curso1);

        Curso resultado = cursoService.create(curso);

        assertEquals(curso1, resultado);
    }
    @Test
    void Update() {
        Curso curso = new Curso(null, "POO","Programación orientada a objetos",5,25);
        Curso actualizado = new Curso(1, "Kotlin","Programación moderna",5,25);

        Mockito.when(cursoClient.update(1, curso)).thenReturn(actualizado);

        Curso curso1 = cursoService.update(1, curso);

        assertEquals(1, curso.getId());
        assertEquals(actualizado, curso1);
    }
    @Test
    void Delete() {
        Curso curso = new Curso(1, "Matemáticas","Algebra",4,15);

        Mockito.when(cursoClient.readById(1)).thenReturn(Optional.of(curso));
        Mockito.when(cursoClient.delete(1)).thenReturn("El curso ha sido borrado");

        String resultado = cursoService.delete(1);

        assertEquals("El curso ha sido borrado", resultado);
        verify(cursoClient).delete(1);
    }


}
