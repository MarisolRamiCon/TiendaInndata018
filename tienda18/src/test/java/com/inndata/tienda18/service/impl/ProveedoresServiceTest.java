package com.inndata.tienda18.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.inndata.tienda18.entity.Proveedores;
import com.inndata.tienda18.model.ProveedoresRequest;
import com.inndata.tienda18.model.ProveedoresResponse;
import com.inndata.tienda18.model.ProveedoresStringResponse;
import com.inndata.tienda18.repository.ProveedoresRepository;

@ExtendWith(MockitoExtension.class)
class ProveedoresServiceTest {

    @Mock
    ProveedoresRepository proveedoresRepository;


    @InjectMocks
    ProveedoresService proveedoresService;



    Proveedores prov1;
    Proveedores prov2;


    @BeforeEach
    void setUp() {
        
        System.out.println("before proveedores tests");

        prov1 = new Proveedores();
        prov1.setIdProveedor(1);
        prov1.setNombreProveedor("Proveedor A");
        prov1.setCorreoElectronicoProveedor("a@prov.com");
        prov1.setTelefonoProveedor("111222333");
        prov1.setActivo(true); // el servicio filtra por activo.equals(false)


        prov2 = new Proveedores();
        prov2.setIdProveedor(2);
        prov2.setNombreProveedor("Proveedor B");
        prov2.setCorreoElectronicoProveedor("b@prov.com");
        prov2.setTelefonoProveedor("444555666");
        prov2.setActivo(true);
    }


    @AfterEach
    void tearDown() {
        System.out.println("after proveedores tests");
    }

    @Test
    void testReadAll() {

        List<Proveedores> lista = List.of(prov1, prov2);
        when(proveedoresRepository.findAll()).thenReturn(lista);

        List<ProveedoresResponse> resultado = proveedoresService.readAll();

        verify(proveedoresRepository, times(1)).findAll();
        System.out.println("resultado = " + resultado);

        List<ProveedoresResponse> esperado = List.of(
            new ProveedoresResponse(prov1.getIdProveedor(),
                                    prov1.getNombreProveedor(),
                                    prov1.getCorreoElectronicoProveedor()),
            new ProveedoresResponse(prov2.getIdProveedor(),
                                    prov2.getNombreProveedor(),
                                    prov2.getCorreoElectronicoProveedor())
        );

        assertEquals(esperado, resultado);
    }


    @Test
    void testReadById_Present() {
        when(proveedoresRepository.findById(1)).thenReturn(Optional.of(prov1));
        ProveedoresResponse resultado = proveedoresService.readById(1);
        assertEquals(new ProveedoresResponse(1, prov1.getNombreProveedor(), prov1.getCorreoElectronicoProveedor()), resultado);
    }

    @Test
    void testReadById_NotPresent() {
        when(proveedoresRepository.findById(999)).thenReturn(Optional.empty());
        ProveedoresResponse resultado = proveedoresService.readById(999);
        assertEquals(new ProveedoresResponse(), resultado);
    }


    @Test
    void testCreate_NewProveedor() {
        List<Proveedores> lista = List.of(prov1, prov2);
        when(proveedoresRepository.findAll()).thenReturn(lista);

        List<ProveedoresResponse> resultado = proveedoresService.readAll();

        verify(proveedoresRepository, times(1)).findAll();
        System.out.println("resultado = " + resultado);

        assertEquals(2, resultado.size());
        assertEquals(prov1.getIdProveedor(), resultado.get(0).getId());
        assertEquals(prov1.getNombreProveedor(), resultado.get(0).getNombre());
        assertEquals(prov1.getCorreoElectronicoProveedor(), resultado.get(0).getCorreoElectronicoProveedor());
        
        assertEquals(prov2.getIdProveedor(), resultado.get(1).getId());
        assertEquals(prov2.getNombreProveedor(), resultado.get(1).getNombre());
        assertEquals(prov2.getCorreoElectronicoProveedor(), resultado.get(1).getCorreoElectronicoProveedor());
    }

    @Test
    void testCreate_ExistingProveedor_ReturnsEmptyResponse() {
        ProveedoresRequest req = new ProveedoresRequest();
        req.setId(prov1.getIdProveedor());
        when(proveedoresRepository.findById(prov1.getIdProveedor())).thenReturn(Optional.of(prov1));
        ProveedoresResponse resultado = proveedoresService.create(req);
        assertEquals(new ProveedoresResponse(), resultado);
    }


    @Test
    void testUpdate_Present() {
        ProveedoresRequest req = new ProveedoresRequest();
        req.setId(1);
        req.setNombre("Proveedor Upd");
        req.setContactoProveedor("Contacto Upd");
        req.setCorreoElectronicoProveedor("upd@prov.com");
        req.setTelefonoProveedor("000111222");
        when(proveedoresRepository.findById(1)).thenReturn(Optional.of(prov1));
        when(proveedoresRepository.save(any(Proveedores.class))).thenAnswer(invocation -> invocation.getArgument(0));
        ProveedoresResponse resultado = proveedoresService.update(1, req);
        assertEquals(1, resultado.getId());
        assertEquals("Proveedor Upd", resultado.getNombre());
    }

    @Test
    void testUpdate_NotFound() {
        ProveedoresRequest req = new ProveedoresRequest();
        req.setId(999);
        when(proveedoresRepository.findById(999)).thenReturn(Optional.empty());
        ProveedoresResponse resultado = proveedoresService.update(999, req);
        assertEquals(new ProveedoresResponse(), resultado);
    }


    @Test
    void testUpdateById_Present() {
        ProveedoresRequest req = new ProveedoresRequest();
        req.setId(1);
        req.setNombre("UpdById");
        req.setContactoProveedor("C");
        req.setCorreoElectronicoProveedor("byid@prov.com");
        req.setTelefonoProveedor("123123");
        when(proveedoresRepository.findById(1)).thenReturn(Optional.of(prov1));
        when(proveedoresRepository.save(any(Proveedores.class))).thenAnswer(invocation -> invocation.getArgument(0));
        ProveedoresStringResponse resultado = proveedoresService.updateById(1, req);
        assertEquals("Proveedor actualizado exitosamente.", resultado.getMensaje());
    }

    @Test
    void testUpdateById_NotFound() {
        ProveedoresRequest req = new ProveedoresRequest();
        when(proveedoresRepository.findById(999)).thenReturn(Optional.empty());
        ProveedoresStringResponse resultado = proveedoresService.updateById(999, req);
        assertEquals("Proveedor no encontrado.", resultado.getMensaje());
    }


    @Test
    void testDelete_Present() {
        when(proveedoresRepository.findById(1)).thenReturn(Optional.of(prov1));
        prov1.setActivo(false);
        when(proveedoresRepository.save(prov1)).thenReturn(prov1);
        ProveedoresStringResponse resultado = proveedoresService.delete(1);
        assertEquals("Proveedor eliminado exitosamente.", resultado.getMensaje());
    }

    @Test
    void testDelete_NotFound() {
        when(proveedoresRepository.findById(999)).thenReturn(Optional.empty());
        ProveedoresStringResponse resultado = proveedoresService.delete(999);
        assertEquals("No se encuentra el proveedor.", resultado.getMensaje());
    }

    @Test
    void testFindByNombreLike_Success() {
        Proveedores pA = new Proveedores();
        pA.setIdProveedor(1);
        pA.setNombreProveedor("ACME");
        pA.setCorreoElectronicoProveedor("acme@ex.com");
        pA.setActivo(false);
        
        
        List<Proveedores> lista = new ArrayList<>();
        lista.add(pA);
        
        
        when(proveedoresRepository.findByNombreProveedorContainingIgnoreCase("acme")).thenReturn(lista);
        
        
        List<ProveedoresResponse> resultado = proveedoresService.findByNombreLike("acme");
        
        
        List<ProveedoresResponse> esperado = new ArrayList<>();
        esperado.add(new ProveedoresResponse(1, "ACME", "acme@ex.com"));
        
        
        assertEquals(esperado, resultado);
    }


    @Test
    void testFindByNombreLike_EmptyList() {
        when(proveedoresRepository.findByNombreProveedorContainingIgnoreCase("nope")).thenReturn(new ArrayList<>());


        List<ProveedoresResponse> resultado = proveedoresService.findByNombreLike("nope");


        assertEquals(0, resultado.size());
    }


    @Test
    void testFindByNombreLike_NullOrBlank_Throws() {
        
        assertThrows(IllegalArgumentException.class, () -> proveedoresService.findByNombreLike(null));
        
        assertThrows(IllegalArgumentException.class, () -> proveedoresService.findByNombreLike(" "));
    }


    @Test
    void testFindByNombreContacto_Success() {
        Proveedores p = new Proveedores();
        p.setIdProveedor(2);
        p.setNombreProveedor("ProveedorX");
        p.setCorreoElectronicoProveedor("x@prov.com");
        p.setContactoProveedor("Juan Perez");
        p.setActivo(false);


        List<Proveedores> lista = new ArrayList<>();
        lista.add(p);


        when(proveedoresRepository.proveedoresActivosPorContacto("Juan")).thenReturn(lista);


        List<ProveedoresResponse> resultado = proveedoresService.findByNombreContacto("Juan");


        List<ProveedoresResponse> esperado = new ArrayList<>();
        esperado.add(new ProveedoresResponse(2, "ProveedorX", "x@prov.com", "Juan Perez"));


        assertEquals(esperado, resultado);
    }


    @Test
    void testFindByNombreContacto_EmptyList() {
        when(proveedoresRepository.proveedoresActivosPorContacto("noone")).thenReturn(new ArrayList<>());


        List<ProveedoresResponse> resultado = proveedoresService.findByNombreContacto("noone");


        assertEquals(0, resultado.size());
    }


    @Test
    void testFindByNombreContacto_NullOrBlank_Throws() {
        assertThrows(IllegalArgumentException.class, () -> proveedoresService.findByNombreContacto(null));
        assertThrows(IllegalArgumentException.class, () -> proveedoresService.findByNombreContacto(""));
    }
}
