package com.inndata.tienda18.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inndata.tienda18.entity.Productos;
import com.inndata.tienda18.model.ProductosRequest;
import com.inndata.tienda18.model.ProductosResponse;
import com.inndata.tienda18.model.ProductosStringRequest;
import com.inndata.tienda18.model.ProductosStringResponse;
import com.inndata.tienda18.repository.ProductosRepository;
import com.inndata.tienda18.service.IProductosService;

@Service
public class ProductosService implements IProductosService {

    @Autowired  // Inyección de dependencias
    ProductosRepository productosRepository;

    private ProductosRequest toDto(Productos producto) {
    ProductosRequest productoDto = new ProductosRequest();
    productoDto.setId(producto.getIdProducto());
    productoDto.setNombreProducto(producto.getNombreProducto());
    productoDto.setCategoriaProducto(producto.getCategoriaProducto());
    productoDto.setDescripcionProducto(producto.getDescripcionProducto());
    productoDto.setIdProveedor(producto.getIdProveedor());
    productoDto.setStockProducto(producto.getStockProducto());
    productoDto.setPrecioProducto(producto.getPrecioProducto());


    return productoDto;

    }

    @Override
    public List<ProductosRequest> readAll() {
        
        return productosRepository.findAll()
            .stream()
            .map(this::toDto)
            .toList();
    }

    @Override
    public Optional<ProductosRequest> readById(Integer id) {
        return productosRepository.findById(id).map(this::toDto);
    }


    //Modificar
    @Override
    public Productos create(Productos producto) {
        return productosRepository.save(producto);
    }

    @Override
    public Productos update(Productos producto) {
        return productosRepository.save(producto);
    }

    @Override
    public String updateById(Integer id, Productos producto) {
        Optional<Productos> producto1= productosRepository.findById(id);

        if(producto1.isPresent()){
            Productos productoModificado = producto1.get();

            try {
                productoModificado.setNombreProducto(producto.getNombreProducto());
                productoModificado.setDescripcionProducto(producto.getDescripcionProducto());
                productoModificado.setCategoriaProducto(producto.getCategoriaProducto());
                productoModificado.setPrecioProducto(producto.getPrecioProducto());
                productoModificado.setStockProducto(producto.getStockProducto());
                productoModificado.setIdProveedor(producto.getIdProveedor());

                productosRepository.save(productoModificado);
                return ("Producto actualizado");
            } catch (Exception e) {
                return "Tienes que modificar todos los campos excepto el id";
            }
        }else{
            return "No existe registro del Producto introducido.";
        }
    }

    @Override
    public String delete(Integer id) {
        Optional<Productos> producto = productosRepository.findById(id);
        if(producto.isPresent()) {
            Productos producto1= producto.get();
            producto1.setActivo(false);
            productosRepository.save(producto1);
            return "El departamento ha sido borrado";
        }else{
            return "No hay tal departamento";
        }
    }

    @Override
    public List<Productos> findByNombre(String nombre) {
        return productosRepository.findByNombreLike(nombre);
    }

    @Override
    public ProductosResponse create(ProductosRequest producto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public ProductosResponse update(ProductosRequest producto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public ProductosStringResponse updateById(Integer id, ProductosRequest producto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateById'");
    }

    @Override
    public List<ProductosResponse> findByNombre(ProductosStringRequest productoRequest) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByNombre'");
    }
    
}
