package com.inndata.tienda18.controller;

import com.inndata.tienda18.model.Curso;
import com.inndata.tienda18.service.impl.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class CursoController {
    @Autowired
    CursoService cursoService;
    @GetMapping("/curso")
    List<Curso> readAll(){
        return cursoService.readAll();
    }
    @GetMapping("/curso/{id}")
    public Optional<Curso> readById(@PathVariable Integer id){
        return cursoService.readById(id);
    }
    @PostMapping("/curso")
    public Curso create(@RequestBody Curso curso){
        return cursoService.create(curso);
    }
    @PutMapping("/curso/{id}")
    public Curso update(@PathVariable Integer id, @RequestBody Curso curso){
        return cursoService.update(id, curso);
    }
    @DeleteMapping("/curso/{id}")
    public String delete(@PathVariable Integer id){
        return  cursoService.delete(id);
    }
}
