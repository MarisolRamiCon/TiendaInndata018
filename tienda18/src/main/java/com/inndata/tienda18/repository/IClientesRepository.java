package com.inndata.tienda18.repository;

import com.inndata.tienda18.entity.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClientesRepository extends JpaRepository<Clientes, Integer> {

}
