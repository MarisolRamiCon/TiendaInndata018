package com.inndata.tienda18.repository;

import com.inndata.tienda18.entity.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Integer> {
    //METODOS PERSONALIZADOS
    public List<DetallePedido> findAllByOrderByPrecioUnitarioDesc();
    @Query(value = "SELECT * FROM detalles_pedido WHERE activo = true", nativeQuery = true)
    List<DetallePedido> findByActivoTrue();
}
