package com.inndata.tienda18.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
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
import org.springframework.dao.DataAccessResourceFailureException;

import com.inndata.tienda18.entity.Productos;
import com.inndata.tienda18.entity.Proveedores;
import com.inndata.tienda18.model.ProductosRequest;
import com.inndata.tienda18.model.ProductosResponse;
import com.inndata.tienda18.model.ProductosStringResponse;
import com.inndata.tienda18.repository.ProductosRepository;
import com.inndata.tienda18.repository.ProveedoresRepository;


@ExtendWith(MockitoExtension.class)
class ProductosServiceTest {

    static class ProductoYaExisteException extends RuntimeException {
        public ProductoYaExisteException(String message) {
            super(message);
        }
    }

    static class ProveedorNoEncontradoException extends RuntimeException {
        public ProveedorNoEncontradoException(String message) {
            super(message);
        }
    }

    static class ProductosCreateException extends RuntimeException {
        public ProductosCreateException(String message, Throwable cause) {
            super(message, cause);
        }
        public ProductosCreateException(String message) {
            super(message);
        }
    }
    @InjectMocks
    ProductosService productosService;


    @Mock
    ProductosRepository productosRepository;


    @Mock
    ProveedoresRepository proveedoresRepository;


    Productos p1;
    Productos p2;
    Proveedores prov1;


    @BeforeEach
    void setUp() {

        System.out.println("before productos tests");


        prov1 = new Proveedores();
        prov1.setIdProveedor(1);
        prov1.setNombreProveedor("Proveedor A");


        p1 = new Productos();
        p1.setIdProducto(100);
        p1.setNombreProducto("Producto 100");
        p1.setDescripcionProducto("Desc 100");
        p1.setPrecioProducto(150.0);
        p1.setCategoriaProducto("Cat A");
        p1.setStockProducto(10);
        p1.setActivo(true); // observa que el service filtra por activo.equals(false)
        p1.setProveedor(prov1);


        p2 = new Productos();
        p2.setIdProducto(101);
        p2.setNombreProducto("Producto 101");
        p2.setDescripcionProducto("Desc 101");
        p2.setPrecioProducto(250.0);
        p2.setCategoriaProducto("Cat B");
        p2.setStockProducto(5);
        p2.setActivo(true);



        
    }


    @AfterEach
    void tearDown() {
        System.out.println("after productos tests");
    }


   @Test
    void testReadAll_returnsOnlyActive() {
        List<Productos> lista = new ArrayList<>();
        lista.add(p1);
        lista.add(p2);
        
        
        when(productosRepository.findAll()).thenReturn(lista);
        
        
        List<ProductosResponse> resultado = productosService.readAll();
        
        
        assertEquals(2, resultado.size());
        assertEquals(p1.getIdProducto(), resultado.get(0).getId());
        assertEquals(p1.getNombreProducto(), resultado.get(0).getNombre());
        assertEquals(p2.getIdProducto(), resultado.get(1).getId());
        assertEquals(p2.getNombreProducto(), resultado.get(1).getNombre());
        
        
        verify(productosRepository, times(1)).findAll();
    }


    @Test
    void testReadAll_filtersOutInactive() {
        p2.setActivo(false); // ahora p2 no debería aparecer en el resultado
        List<Productos> lista = List.of(p1, p2);
        when(productosRepository.findAll()).thenReturn(lista);


        List<ProductosResponse> resultado = productosService.readAll();


        assertEquals(1, resultado.size());
        assertEquals(p1.getIdProducto(), resultado.get(0).getId());
        assertEquals(p1.getNombreProducto(), resultado.get(0).getNombre());
    }


    @Test
    void testReadAll_throwsProductosReadException_onDataAccessError() {
        when(productosRepository.findAll()).thenThrow(new DataAccessResourceFailureException("DB down"));


        RuntimeException ex = assertThrows(RuntimeException.class, () -> productosService.readAll());
        assertTrue(ex instanceof ProductosService.ProductosReadException || ex.getMessage().contains("Error al acceder a la base de datos"));


        verify(productosRepository, times(1)).findAll();
    }


    @Test
    void readById_returnsProduct_whenFound() {
        when(productosRepository.findById(1)).thenReturn(Optional.of(p1));

        ProductosResponse resp = productosService.readById(1);

        assertNotNull(resp);
        assertEquals(p1.getIdProducto(), resp.getId());
        assertEquals(p1.getNombreProducto(), resp.getNombre());
        assertEquals(p1.getDescripcionProducto(), resp.getDescripcionProducto());
        assertEquals(p1.getPrecioProducto(), resp.getPrecioProducto());
        assertEquals(p1.getCategoriaProducto(), resp.getCategoriaProducto());
        verify(productosRepository, times(1)).findById(1);
    }

    /* assertTrue(ex.getClass().getSimpleName().equals("ProductoNoEncontradoException") ||
                   ex.getMessage().contains("No se encontró un producto"));*/
    @Test
    void readById_throwsProductoNoEncontradoException_whenNotFound() {
        when(productosRepository.findById(99)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> productosService.readById(99));
        // Si definiste ProductoNoEncontradoException como clase interna, comprueba tipo específico:
        
        assertTrue(ex instanceof ProductosService.ProductoNoEncontradoException);
        verify(productosRepository, times(1)).findById(99);
    }

    @Test
    void readById_throwsIllegalArgumentException_whenIdInvalid() {
        // id null
        assertThrows(IllegalArgumentException.class, () -> productosService.readById(null));
        // id <= 0
        assertThrows(IllegalArgumentException.class, () -> productosService.readById(0));
        verify(productosRepository, never()).findById(any());
    }

    @Test
    void readById_throwsProductosReadException_onDataAccessError() {
        when(productosRepository.findById(1)).thenThrow(new DataAccessResourceFailureException("DB down"));

        RuntimeException ex = assertThrows(RuntimeException.class, () -> productosService.readById(1));
        assertTrue(ex instanceof ProductosService.ProductosReadException || ex.getMessage().contains("Error al acceder a la base de datos"));
        verify(productosRepository, times(1)).findById(1);
    }

    @Test
    void testCreate_NewProduct_WithProveedorPresent() {
        ProductosRequest req = new ProductosRequest();
        req.setId(200);
        req.setNombre("Producto 200");
        req.setDescripcionProducto("Desc 200");
        req.setCategoriaProducto("Cat C");
        req.setPrecioProducto(300.0);
        req.setStockProducto(20);
        req.setIdProveedor(1);

        when(productosRepository.findById(200)).thenReturn(Optional.empty());
        when(proveedoresRepository.findById(1)).thenReturn(Optional.of(prov1));
        when(productosRepository.save(any(Productos.class))).thenAnswer(invocation -> {
            Productos p = invocation.getArgument(0);
            p.setIdProducto(200); // Simula que la BD asigna el ID
            return p;
        });

        ProductosResponse resultado = productosService.create(req);

        assertNotNull(resultado);
        assertEquals(200, resultado.getId());
        assertEquals("Producto 200", resultado.getNombre());
        // según tus getters en ProductosResponse:
        assertEquals("Desc 200", resultado.getDescripcionProducto());
        assertEquals(300.0, resultado.getPrecioProducto());
        assertEquals("Cat C", resultado.getCategoriaProducto());

        verify(productosRepository, times(1)).findById(200);
        verify(proveedoresRepository, times(1)).findById(1);
        verify(productosRepository, times(1)).save(any(Productos.class));
    }

    @Test
    void testCreate_ExistingProduct_ThrowsProductoYaExisteException() {
        ProductosRequest req = new ProductosRequest();
        req.setId(p1.getIdProducto()); // 1 (producto ya existe)
        req.setIdProveedor(1);

        when(productosRepository.findById(p1.getIdProducto())).thenReturn(Optional.of(p1));

        // Ahora la implementación lanza ProductoYaExisteException en este caso
        assertThrows(ProductosService.ProductoYaExisteException.class, () -> productosService.create(req));

        verify(productosRepository, times(1)).findById(p1.getIdProducto());
        verify(productosRepository, never()).save(any()); // no debe guardarse
    }

    @Test
    void testCreate_ProveedorNoEncontrado_ThrowsProveedorNoEncontradoException() {
        ProductosRequest req = new ProductosRequest();
        req.setId(null); // nuevo producto (sin id) — típico caso
        req.setNombre("Nuevo");
        req.setIdProveedor(99); // proveedor inexistente

        when(proveedoresRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(ProductosService.ProveedorNoEncontradoException.class, () -> productosService.create(req));

        verify(proveedoresRepository, times(1)).findById(99);
        verify(productosRepository, never()).save(any());
    }

    @Test
    void testCreate_DataAccessException_ThrowsProductosCreateException() {
        ProductosRequest req = new ProductosRequest();
        req.setId(null);
        req.setNombre("Nuevo");
        req.setIdProveedor(1);

        when(proveedoresRepository.findById(1)).thenReturn(Optional.of(prov1));
        when(productosRepository.save(any(Productos.class)))
            .thenThrow(new DataAccessResourceFailureException("DB down"));

        RuntimeException ex = assertThrows(RuntimeException.class, () -> productosService.create(req));
        // Comprueba que sea la excepción personalizada de creación (o al menos su nombre)
        assertTrue(ex instanceof ProductosService.ProductosCreateException
                || ex.getMessage().contains("Error al crear el producto"));

        verify(proveedoresRepository, times(1)).findById(1);
        verify(productosRepository, times(1)).save(any(Productos.class));
    }


    @Test
    void testUpdate_ProductNotFound_ThrowsProductoNoEncontradoException() {
        ProductosRequest req = new ProductosRequest();
        req.setNombre("New Name");

        when(productosRepository.findById(999)).thenReturn(Optional.empty());

        // Esperamos la excepción de dominio definida en el servicio
        assertThrows(ProductosService.ProductoNoEncontradoException.class,
            () -> productosService.update(999, req));

        verify(productosRepository, times(1)).findById(999);
        verify(productosRepository, never()).save(any());
    }

    @Test
    void testUpdate_InvalidId_ThrowsIllegalArgumentException() {
        ProductosRequest req = new ProductosRequest();
        req.setNombre("New Name");

        // id null
        assertThrows(IllegalArgumentException.class, () -> productosService.update(null, req));
        // id <= 0
        assertThrows(IllegalArgumentException.class, () -> productosService.update(0, req));
        // productoRequest null
        assertThrows(IllegalArgumentException.class, () -> productosService.update(1, null));

        verify(productosRepository, never()).findById(any());
    }

    @Test
    void testUpdate_Success() {
        ProductosRequest req = new ProductosRequest();
        req.setNombre("New Name");
        req.setDescripcionProducto("New Desc");
        req.setPrecioProducto(99.99);
        req.setCategoriaProducto("New Cat");
        req.setStockProducto(10);

        when(productosRepository.findById(p1.getIdProducto())).thenReturn(Optional.of(p1));
        // Simular que save devuelve la entidad actualizada (podría devolver una copia)
        when(productosRepository.save(any(Productos.class))).thenAnswer(inv -> inv.getArgument(0));

        ProductosResponse resp = productosService.update(p1.getIdProducto(), req);

        assertNotNull(resp);
        assertEquals(p1.getIdProducto(), resp.getId());
        assertEquals("New Name", resp.getNombre());
        assertEquals("New Desc", resp.getDescripcionProducto());
        assertEquals(99.99, resp.getPrecioProducto());
        assertEquals("New Cat", resp.getCategoriaProducto());

        verify(productosRepository, times(1)).findById(p1.getIdProducto());
        verify(productosRepository, times(1)).save(any(Productos.class));
    }

    @Test
    void testUpdate_DataAccessException_ThrowsProductosUpdateException() {
        ProductosRequest req = new ProductosRequest();
        req.setNombre("New Name");
        when(productosRepository.findById(p1.getIdProducto())).thenReturn(Optional.of(p1));
        when(productosRepository.save(any(Productos.class)))
            .thenThrow(new DataAccessResourceFailureException("DB down"));

        // Una única llamada que debe lanzar
        RuntimeException ex = assertThrows(RuntimeException.class, 
            () -> productosService.update(p1.getIdProducto(), req));
        // Comprobamos que sea la excepción personalizada (o por lo menos el mensaje/nombre)
        assertTrue(ex instanceof ProductosService.ProductosUpdateException
                || ex.getMessage().contains("Error al actualizar el producto"));

        verify(productosRepository, times(1)).findById(p1.getIdProducto());
        verify(productosRepository, times(1)).save(any(Productos.class));
    }

    @Test
    void testUpdateById_Present_Success() {
        ProductosRequest req = new ProductosRequest();
        req.setId(100);
        req.setNombre("ById Updated");
        req.setDescripcionProducto("Desc ById");
        req.setCategoriaProducto("Cat X");
        req.setPrecioProducto(123.0);
        req.setStockProducto(7);

        when(productosRepository.findById(100)).thenReturn(Optional.of(p1));
        when(productosRepository.save(any(Productos.class))).thenAnswer(inv -> inv.getArgument(0));

        ProductosStringResponse resultado = productosService.updateById(100, req);

        assertNotNull(resultado);
        assertEquals("Producto actualizado exitosamente.", resultado.getMessage());

        verify(productosRepository, times(1)).findById(100);
        verify(productosRepository, times(1)).save(any(Productos.class));
    }

    @Test
    void testUpdateById_NotFound_ThrowsProductoNoEncontradoException() {
        ProductosRequest req = new ProductosRequest();
        when(productosRepository.findById(999)).thenReturn(Optional.empty());

        assertThrows(ProductosService.ProductoNoEncontradoException.class, () ->
            productosService.updateById(999, req)
        );

        verify(productosRepository, times(1)).findById(999);
        verify(productosRepository, never()).save(any());
    }

    @Test
    void testUpdateById_InvalidIdOrRequest_ThrowsIllegalArgumentException() {
        ProductosRequest req = new ProductosRequest();
        req.setNombre("Any");

        // id null
        assertThrows(IllegalArgumentException.class, () -> productosService.updateById(null, req));
        // id <= 0
        assertThrows(IllegalArgumentException.class, () -> productosService.updateById(0, req));
        // productoRequest null
        assertThrows(IllegalArgumentException.class, () -> productosService.updateById(100, null));

        verify(productosRepository, never()).findById(any());
        verify(productosRepository, never()).save(any());
    }

    @Test
    void testUpdateById_DataAccessError_ThrowsProductosUpdateException() {
        ProductosRequest req = new ProductosRequest();
        req.setId(100);
        req.setNombre("ById Updated");

        when(productosRepository.findById(p1.getIdProducto())).thenReturn(Optional.of(p1));
        when(productosRepository.save(any(Productos.class)))
            .thenThrow(new DataAccessResourceFailureException("DB down"));

        // Una única llamada aislada
        RuntimeException ex = assertThrows(RuntimeException.class, 
            () -> productosService.updateById(p1.getIdProducto(), req));

        // Comprueba nombre o mensaje para confirmar que es la excepción personalizada
        assertTrue(ex instanceof ProductosService.ProductosUpdateException
                || ex.getMessage().contains("Error al actualizar el producto"));

        verify(productosRepository, times(1)).findById(p1.getIdProducto());
        verify(productosRepository, times(1)).save(any(Productos.class));
    }

    @Test
    void testDelete_Present_Success() {
        when(productosRepository.findById(100)).thenReturn(Optional.of(p1));
        // Simular que save devuelve la entidad (con activo = false ya seteado por el servicio)
        when(productosRepository.save(any(Productos.class))).thenAnswer(inv -> inv.getArgument(0));

        ProductosStringResponse resultado = productosService.delete(100);

        assertNotNull(resultado);
        assertEquals("Producto eliminado exitosamente.", resultado.getMessage());

        verify(productosRepository, times(1)).findById(100);
        verify(productosRepository, times(1)).save(any(Productos.class));
    }

    @Test
    void testDelete_NotFound_ThrowsProductoNoEncontradoException() {
        when(productosRepository.findById(999)).thenReturn(Optional.empty());

        assertThrows(ProductosService.ProductoNoEncontradoException.class,
            () -> productosService.delete(999)
        );

        verify(productosRepository, times(1)).findById(999);
        verify(productosRepository, never()).save(any());
    }

    @Test
    void testDelete_InvalidId_ThrowsIllegalArgumentException() {
        // id null
        assertThrows(IllegalArgumentException.class, () -> productosService.delete(null));
        // id <= 0
        assertThrows(IllegalArgumentException.class, () -> productosService.delete(0));
        assertThrows(IllegalArgumentException.class, () -> productosService.delete(-5));

        verify(productosRepository, never()).findById(any());
        verify(productosRepository, never()).save(any());
    }

    @Test
    void testDelete_DataAccessException_ThrowsProductosDeleteException() {
        when(productosRepository.findById(p1.getIdProducto())).thenReturn(Optional.of(p1));
        when(productosRepository.save(any(Productos.class)))
            .thenThrow(new DataAccessResourceFailureException("DB down"));

        RuntimeException ex = assertThrows(RuntimeException.class,
            () -> productosService.delete(p1.getIdProducto())
        );

        // Comprobamos que sea la excepción personalizada de borrado o al menos su mensaje/nombre
        assertTrue(ex instanceof ProductosService.ProductosDeleteException
                || ex.getMessage().contains("Error al eliminar el producto"));

        verify(productosRepository, times(1)).findById(p1.getIdProducto());
        verify(productosRepository, times(1)).save(any(Productos.class));
    }

    @Test
    void testProductosPrecioMayorQue_returnsMappedList() {
        List<Productos> lista = new ArrayList<>();
        lista.add(p1);

        when(productosRepository.productosPrecioMayorQue(100.0)).thenReturn(lista);

        List<ProductosResponse> resultado = productosService.productosPrecioMayorQue(100.0);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(p1.getIdProducto(), resultado.get(0).getId());
        assertEquals(p1.getNombreProducto(), resultado.get(0).getNombre());

        verify(productosRepository, times(1)).productosPrecioMayorQue(100.0);
    }

    @Test
    void testProductosPrecioMayorQue_throwsOnNullPrice() {
        assertThrows(IllegalArgumentException.class, () -> productosService.productosPrecioMayorQue(null));
        verify(productosRepository, never()).productosPrecioMayorQue(anyDouble());
    }

    @Test
    void testProductosPrecioMayorQue_throwsOnNegativePrice() {
        assertThrows(IllegalArgumentException.class, () -> productosService.productosPrecioMayorQue(-1.0));
        verify(productosRepository, never()).productosPrecioMayorQue(anyDouble());
    }

    @Test
    void testProductosPrecioMayorQue_throwsProductosQueryException_onDataAccessError() {
        when(productosRepository.productosPrecioMayorQue(100.0))
            .thenThrow(new DataAccessResourceFailureException("DB down"));

        RuntimeException ex = assertThrows(RuntimeException.class, () -> productosService.productosPrecioMayorQue(100.0));

        // Si definiste ProductosQueryException como clase interna, puedes lanzar su tipo específico.
        // Aquí comprobamos por el nombre o por el mensaje para mantener el test robusto.
        assertTrue(ex instanceof ProductosService.ProductosQueryException
                || ex.getMessage().contains("Error al buscar productos por precio"));

        verify(productosRepository, times(1)).productosPrecioMayorQue(100.0);
    }

    @Test
    void testFindByNombreLike_returnsMappedList() {
        List<Productos> lista = new ArrayList<>();
        lista.add(p1);

        when(productosRepository.findByNombreProductoContainingIgnoreCase("prod")).thenReturn(lista);

        List<ProductosResponse> resultado = productosService.findByNombreLike("prod");

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(p1.getIdProducto(), resultado.get(0).getId());
        assertEquals(p1.getNombreProducto(), resultado.get(0).getNombre());

        verify(productosRepository, times(1)).findByNombreProductoContainingIgnoreCase("prod");
    }

    @Test
    void testFindByNombreLike_returnsEmptyListWhenNoMatches() {
        when(productosRepository.findByNombreProductoContainingIgnoreCase("noexiste"))
            .thenReturn(List.of());

        List<ProductosResponse> resultado = productosService.findByNombreLike("noexiste");

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());

        verify(productosRepository, times(1)).findByNombreProductoContainingIgnoreCase("noexiste");
    }

    @Test
    void testFindByNombreLike_throwsOnNullOrBlank() {
        // null
        assertThrows(IllegalArgumentException.class, () -> productosService.findByNombreLike(null));
        // blank
        assertThrows(IllegalArgumentException.class, () -> productosService.findByNombreLike("   "));

        verify(productosRepository, never()).findByNombreProductoContainingIgnoreCase(anyString());
    }

    @Test
    void testFindByNombreLike_throwsProductosQueryException_onDataAccessError() {
        when(productosRepository.findByNombreProductoContainingIgnoreCase("prod"))
            .thenThrow(new DataAccessResourceFailureException("DB down"));

        RuntimeException ex = assertThrows(RuntimeException.class, () -> productosService.findByNombreLike("prod"));

        // Comprueba por nombre o mensaje para ser robusto si la excepción es interna
        assertTrue(ex instanceof ProductosService.ProductosQueryException
                || ex.getMessage().contains("Error al buscar productos por nombre"));

        verify(productosRepository, times(1)).findByNombreProductoContainingIgnoreCase("prod");
    }



}
