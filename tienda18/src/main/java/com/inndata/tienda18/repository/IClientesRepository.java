package com.inndata.tienda18.repository;
import com.inndata.tienda18.entity.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IClientesRepository extends JpaRepository<Clientes, Integer> {
    /*
    //Metodos personalizados con palabras claves de Jpa
    public List<Departamento> findByPrecioGreaterThan(Double precio);
    //buscar los departamentos donde los m2 son menor o igual que 100 y precio es mayor o igual que 1500
    public List<Departamento> findByM2LessThanEqualAndPrecioGreaterThanEqual(Integer m2, Double precio);
    //Metodos personalizados por medio de Query
    @Query(value = "select * from departamento where m2<=:m2 and precio >=:precio", nativeQuery = true)
    public List<Departamento> m2AndPrecio(Integer m2, Double precio);
    */


}
