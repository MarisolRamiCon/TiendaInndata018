package com.inndata.tienda18.feign;

import com.inndata.tienda18.model.Curso;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "curso", url = "https://691be2293aaeed735c8e9e69.mockapi.io/api/v1")
public interface CursoClient {
    @GetMapping("/curso")
    public List<Curso> readAll();
    @GetMapping("/curso/{id}")
    public Optional<Curso> readById(@PathVariable Integer id);
    @PostMapping("/curso")
    public Curso create(@RequestBody Curso curso);
    @PutMapping("/curso/{id}")
    public Curso update(@PathVariable Integer id, @RequestBody Curso curso);
    @DeleteMapping("/curso/{id}")
    public String delete(@PathVariable Integer id);
}
