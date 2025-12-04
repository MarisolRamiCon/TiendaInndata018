package com.inndata.tienda18.service.impl;


import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import com.inndata.tienda18.entity.Productos;
import com.inndata.tienda18.entity.Proveedores;
import com.inndata.tienda18.model.ProductosRequest;
import com.inndata.tienda18.model.ProductosResponse;
import com.inndata.tienda18.model.ProductosStringResponse;
import com.inndata.tienda18.repository.ProductosRepository;
import com.inndata.tienda18.repository.ProveedoresRepository;
import com.inndata.tienda18.service.IProductosService;


@Service
public class ProductosService implements IProductosService {


    public static class ProductosReadException extends RuntimeException {
        public ProductosReadException(String message, Throwable cause) {
            super(message, cause);
        }
    }
    public static class ProductoNoEncontradoException extends RuntimeException {
        public ProductoNoEncontradoException(String message) {
            super(message);
        }
    }
    public static class ProductosCreateException extends RuntimeException {
        public ProductosCreateException(String message, Throwable cause) {
            super(message, cause);
        }
    }
    public static class ProductoYaExisteException extends RuntimeException {
        public ProductoYaExisteException(String message) {
            super(message);
        }
    }
    public static class ProveedorNoEncontradoException extends RuntimeException {
        public ProveedorNoEncontradoException(String message) {
            super(message);
        }
    }

    /* Excepciones internas (defínelas una sola vez por clase; si ya existen, elimínalas aquí) */
    public static class ProductosUpdateException extends RuntimeException {
        public ProductosUpdateException(String message, Throwable cause) {
            super(message, cause);
        }
    }
    public static class ProductosDeleteException extends RuntimeException {
        public ProductosDeleteException(String message, Throwable cause) {
            super(message, cause);
        }
    }
    public static class ProductosQueryException extends RuntimeException {
        public ProductosQueryException(String message, Throwable cause) {
            super(message, cause);
        }
    }


    

    private final ProductosRepository productosRepository;
    private final ProveedoresRepository proveedoresRepository;

    // Constructor con inyección de dependencias - SIN @Autowired
    public ProductosService(ProductosRepository productosRepository, ProveedoresRepository proveedoresRepository) {
        this.productosRepository = productosRepository;
        this.proveedoresRepository = proveedoresRepository;
    }

    

    
    @Override
    public List<ProductosResponse> readAll() {
        try {
            return productosRepository.findAll()
            .stream()
            .filter(x -> Boolean.TRUE.equals(x.getActivo()))
            .map(producto -> new ProductosResponse(
                producto.getIdProducto(),
                producto.getNombreProducto(),
                producto.getDescripcionProducto(),
                producto.getPrecioProducto(),
                producto.getCategoriaProducto()
            ))
            .toList();


        } catch (DataAccessException dae) {
            // Error específico de acceso a datos
            throw new ProductosReadException("Error al acceder a la base de datos mientras se leían los productos", dae);
            
        
        } catch (NullPointerException npe) {
            // Error específico por datos inesperadamente nulos
            throw new IllegalStateException("Se encontró un valor nulo inesperado al procesar los productos", npe);
        
        
        } catch (Exception e) {
            // Cualquier otro error inesperado
            throw new ProductosReadException("Ocurrió un error inesperado al leer los productos", e);
        }
    }

    
    @Override
    public ProductosResponse readById(Integer id) {
        try {
            // Validación de parámetro
            if (id == null || id <= 0) {
                throw new IllegalArgumentException("El ID del producto debe ser un número positivo.");
            }
            Optional<Productos> producto = productosRepository.findById(id);

            if (producto.isPresent()) {
                Productos p = producto.get();
                return new ProductosResponse(
                    p.getIdProducto(),
                    p.getNombreProducto(),
                    p.getDescripcionProducto(),
                    p.getPrecioProducto(),
                    p.getCategoriaProducto()
                );
            } else {
                // Producto no encontrado
                throw new ProductoNoEncontradoException("No se encontró un producto con el ID: " + id);
            }

        } catch (DataAccessException dae) {
            // Error específico de acceso a datos
            throw new ProductosReadException("Error al acceder a la base de datos al consultar el producto con ID: " + id, dae);

        } catch (IllegalArgumentException iae) {
            // Error por argumento inválido
            throw iae;

        } catch (ProductoNoEncontradoException pnfe) {
            // Se relanza tal cual
            throw pnfe;

        } catch (Exception e) {
            // Error inesperado
            throw new ProductosReadException("Ocurrió un error inesperado al leer el producto con ID: " + id, e);
        }
    }

    @Transactional
    @Override
    public ProductosResponse create(ProductosRequest productoRequest) {
        try {
            // Validaciones de entrada
            if (productoRequest == null) {
                throw new IllegalArgumentException("El cuerpo de la petición productoRequest no puede ser nulo.");
            }
            if (productoRequest.getIdProveedor() == null) {
                throw new IllegalArgumentException("El campo idProveedor es requerido.");
            }


            // Si el cliente envía un id de producto, comprobar si ya existe
            if (productoRequest.getId() != null) {
                    Optional<Productos> productoFound = productosRepository.findById(productoRequest.getId());
                if (productoFound.isPresent()) {
                    throw new ProductoYaExisteException("Ya existe un producto con el ID: " + productoRequest.getId());
                }
            }


            // Buscar proveedor
            Optional<Proveedores> proveedorOpt = proveedoresRepository.findById(productoRequest.getIdProveedor());
            if (proveedorOpt.isEmpty()) {
                throw new ProveedorNoEncontradoException("No se encontró el proveedor con ID: " + productoRequest.getIdProveedor());
            }

            // Construir entidad y persistir
            Productos producto = new Productos();
            producto.setNombreProducto(productoRequest.getNombre());
            producto.setCategoriaProducto(productoRequest.getCategoriaProducto());
            producto.setDescripcionProducto(productoRequest.getDescripcionProducto());
            producto.setPrecioProducto(productoRequest.getPrecioProducto());
            producto.setStockProducto(productoRequest.getStockProducto());
            producto.setProveedor(proveedorOpt.get());


            Productos saved = productosRepository.save(producto);


            return new ProductosResponse(
                saved.getIdProducto(),
                saved.getNombreProducto(),
                saved.getDescripcionProducto(),
                saved.getPrecioProducto(),
                saved.getCategoriaProducto()
            );


        } catch (DataAccessException dae) {
            // Error específico de la capa de persistencia
            throw new ProductosCreateException("Error al acceder a la base de datos al crear el producto", dae);
        
        
        } catch (IllegalArgumentException iae) {
            // Re-lanzar IllegalArgumentException para que el controlador lo gestione si corresponde
            throw iae;
        
        
        } catch (ProductoYaExisteException | ProveedorNoEncontradoException pex) {
            // Re-lanzamos excepciones de dominio tal cual para que sean manejadas por capas superiores
            throw pex;
        
        
        } catch (Exception e) {
            // Cualquier otro error inesperado
            throw new ProductosCreateException("Ocurrió un error inesperado al crear el producto", e);
        }
    }

    @Transactional
    @Override
    public ProductosResponse update(Integer id, ProductosRequest productoRequest) {
        try {
            // Validaciones básicas
            if (id == null || id <= 0) {
                throw new IllegalArgumentException("El ID del producto debe ser un número positivo.");
            }
            if (productoRequest == null) {
                throw new IllegalArgumentException("El productoRequest no puede ser nulo.");
            }

            Optional<Productos> productoOpt = productosRepository.findById(id);

            if (productoOpt.isEmpty()) {
                throw new ProductoNoEncontradoException("No se encontró un producto con ID: " + id);
            }

            Productos productoAModificar = productoOpt.get();

            // Actualizar campos (puedes agregar defensivas para valores nulos si lo deseas)
            productoAModificar.setNombreProducto(productoRequest.getNombre());
            productoAModificar.setCategoriaProducto(productoRequest.getCategoriaProducto());
            productoAModificar.setDescripcionProducto(productoRequest.getDescripcionProducto());
            productoAModificar.setPrecioProducto(productoRequest.getPrecioProducto());
            productoAModificar.setStockProducto(productoRequest.getStockProducto());

            Productos saved = productosRepository.save(productoAModificar);

            return new ProductosResponse(
                saved.getIdProducto(),
                saved.getNombreProducto(),
                saved.getDescripcionProducto(),
                saved.getPrecioProducto(),
                saved.getCategoriaProducto()
            );

        } catch (DataAccessException dae) {
            // Error en la capa de persistencia / BD
            throw new ProductosUpdateException("Error al acceder a la base de datos al actualizar el producto con ID: " + id, dae);

        } catch (IllegalArgumentException iae) {
            // Re-lanzar para que capas superiores decidan (ej. controlador)
            throw iae;

        } catch (ProductoNoEncontradoException pnfe) {
            // Re-lanzar para que capas superiores lo manejen (ej. 404 en controlador)
            throw pnfe;

        } catch (Exception e) {
            // Cualquier otro error inesperado
            throw new ProductosUpdateException("Ocurrió un error inesperado al actualizar el producto con ID: " + id, e);
        }
    }

    @Transactional
    @Override
    public ProductosStringResponse updateById(Integer id, ProductosRequest productoRequest) {
        try {
            // Validaciones básicas
            if (id == null || id <= 0) {
                throw new IllegalArgumentException("El ID del producto debe ser un número positivo.");
            }
            if (productoRequest == null) {
                throw new IllegalArgumentException("El productoRequest no puede ser nulo.");
            }

            Optional<Productos> productoOpt = productosRepository.findById(id);

            if (productoOpt.isEmpty()) {
                throw new ProductoNoEncontradoException("No se encontró un producto con ID: " + id);
            }

            Productos productoAModificar = productoOpt.get();

            // Actualizar campos (validar campos opcionales si es necesario)
            productoAModificar.setNombreProducto(productoRequest.getNombre());
            productoAModificar.setCategoriaProducto(productoRequest.getCategoriaProducto());
            productoAModificar.setDescripcionProducto(productoRequest.getDescripcionProducto());
            productoAModificar.setPrecioProducto(productoRequest.getPrecioProducto());
            // Atención: normalmente no se recomienda cambiar el ID de la entidad; aquí lo dejo
            // como en tu implementación original, pero valora si realmente lo necesitas:
            productoAModificar.setIdProducto(productoRequest.getId());
            productoAModificar.setStockProducto(productoRequest.getStockProducto());

            productosRepository.save(productoAModificar);

            return new ProductosStringResponse("Producto actualizado exitosamente.");

        } catch (DataAccessException dae) {
            // Error en la capa de persistencia
            throw new ProductosUpdateException("Error al actualizar el producto con ID: " + id + " en la base de datos.", dae);
        
        } catch (IllegalArgumentException iae) {
            // Re-lanzar para que el controlador lo maneje como 400 si corresponde
            throw iae;
        
        } catch (ProductoNoEncontradoException pnfe) {
            // Re-lanzar para que capas superiores (ej. controlador) lo manejen (404)
            throw pnfe;
        
        } catch (Exception e) {
            // Cualquier otro error inesperado
            throw new ProductosUpdateException("Ocurrió un error inesperado al actualizar el producto con ID: " + id, e);
        }
    }

    @Transactional
    @Override
    public ProductosStringResponse delete(Integer id) {
        try {
            // Validar ID
            if (id == null || id <= 0) {
                throw new IllegalArgumentException("El ID del producto debe ser un número positivo.");
            }

            Optional<Productos> productoAEliminar = productosRepository.findById(id);

            if (productoAEliminar.isEmpty()) {
                throw new ProductoNoEncontradoException("No se encontró un producto con el ID: " + id);
            }

            Productos producto = productoAEliminar.get();
            producto.setActivo(false); // Eliminación lógica
            productosRepository.save(producto);

            return new ProductosStringResponse("Producto eliminado exitosamente.");

        } catch (DataAccessException dae) {
            // Error específico de acceso a la base de datos
            throw new ProductosDeleteException("Error al eliminar el producto con ID: " + id, dae);

        } catch (IllegalArgumentException iae) {
            throw iae; // Se relanza para que el controlador devuelva 400 si aplica

        } catch (ProductoNoEncontradoException pnfe) {
            throw pnfe; // Se relanza para que el controlador devuelva 404

        } catch (Exception e) {
            throw new ProductosDeleteException("Error inesperado al eliminar el producto con ID: " + id, e);
        }
    }


    @Override
    public List<ProductosResponse> productosPrecioMayorQue(Double precio) {
        try {
            // Validación de entrada
            if (precio == null) {
                throw new IllegalArgumentException("El parámetro 'precio' no puede estar vacío.");
            }
            if (precio < 0) {
                throw new IllegalArgumentException("El parámetro 'precio' debe ser mayor o igual a 0.");
            }

            return productosRepository.productosPrecioMayorQue(precio)
                .stream()
                .map(producto -> new ProductosResponse(
                    producto.getIdProducto(),
                    producto.getNombreProducto(),
                    producto.getDescripcionProducto(),
                    producto.getPrecioProducto(),
                    producto.getCategoriaProducto()
                ))
                .toList();

        } catch (DataAccessException dae) {
            throw new ProductosQueryException("Error al consultar productos con precio mayor que: " + precio, dae);

        } catch (IllegalArgumentException iae) {
            // Re-lanzar para que la capa superior (controlador) lo traduzca a 400 si corresponde
            throw iae;

        } catch (Exception e) {
            throw new ProductosQueryException("Ocurrió un error inesperado al consultar productos por precio.", e);
        }
    }


    @Override
    public List<ProductosResponse> findByNombreLike(String nombreProducto) {
        try {
            // Validación de entrada
            if (nombreProducto == null || nombreProducto.isBlank()) {
                throw new IllegalArgumentException("El parámetro 'nombre' es requerido.");
            }

            return productosRepository.findByNombreProductoContainingIgnoreCase(nombreProducto)
                .stream()
                .map(producto -> new ProductosResponse(
                    producto.getIdProducto(),
                    producto.getNombreProducto(),
                    producto.getDescripcionProducto(),
                    producto.getPrecioProducto(),
                    producto.getCategoriaProducto()
                ))
                .toList();

        } catch (DataAccessException dae) {
            throw new ProductosQueryException("Error al buscar productos por nombre: " + nombreProducto, dae);

        } catch (IllegalArgumentException iae) {
            // Re-lanzar para que la capa superior lo maneje
            throw iae;

        } catch (Exception e) {
            throw new ProductosQueryException("Ocurrió un error inesperado al buscar productos por nombre.", e);
        }
        }
}