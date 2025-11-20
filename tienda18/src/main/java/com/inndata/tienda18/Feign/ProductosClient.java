
package com.inndata.tienda18.Feign;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.inndata.tienda18.model.Productos;




@FeignClient(name = "Productos", url = "https://691696fba7a34288a27dc06d.mockapi.io/api/v1")
public interface ProductosClient {
    @GetMapping("/Productos")
    public List<Productos> readAll();

    @GetMapping("/Productos/{id}")
    public Optional<Productos> readById(@PathVariable("id") Integer id);

    @PostMapping("/Productos")
    public Productos create(@RequestBody Productos producto);

    @PutMapping("/Productos/{id}")
    public String updateById(@PathVariable Integer id, @RequestBody Productos producto);

    @DeleteMapping("/Productos/{id}")
    public String delete(@PathVariable("id") Integer id);
}