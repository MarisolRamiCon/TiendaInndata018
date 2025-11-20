package com.inndata.tienda18.repository;

import com.inndata.tienda18.entity.Pedido;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    public List<Pedido> findByFechaPedidoBetween(LocalDate inicio, LocalDate fin);
    @Query(value = "SELECT * FROM pedidos WHERE total_pedido > :total", nativeQuery = true)
    List<Pedido> findByTotalPedidoGreaterThan(@Param("total") Double total);
}
