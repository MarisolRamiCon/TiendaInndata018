package com.inndata.tienda18.service.impl;

import com.inndata.tienda18.feign.CursoClient;
import com.inndata.tienda18.model.Curso;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService implements CursoClient {
    @Autowired
    CursoClient cursoClient;
    @Override
    public List<Curso> readAll() {
        return cursoClient.readAll();
    }

    @Override
    public Optional<Curso> readById(Integer id) {
        return cursoClient.readById(id);
    }

    @Override
    public Curso create(Curso curso) {
        return cursoClient.create(curso);
    }

    @Override
    public Curso update(Integer id, Curso curso) {
        curso.setId(id);
        return cursoClient.update(id, curso);
    }

    @Override
    public String delete(Integer id) {
        try {
            cursoClient.readById(id);
            cursoClient.delete(id);
            return "El curso ha sido borrado";
        }catch (FeignException.NotFound e){
            return "No se encuentra el curso";
        }
    }
}
