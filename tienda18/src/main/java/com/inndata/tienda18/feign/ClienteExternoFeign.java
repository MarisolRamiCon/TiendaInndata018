package com.inndata.tienda18.feign;

import com.inndata.tienda18.model.ClienteExternoModel;
import jakarta.websocket.server.PathParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "clienteExterno", url = "https://691f7bef31e684d7bfc9daa7.mockapi.io/api/v4")
public interface ClienteExternoFeign {
    //Interfase Entidad Service

    @GetMapping("/clienteExterno")
    public List<ClienteExternoModel> readAll();

    @GetMapping("/clienteExterno/{idCteExt}")
    public Optional<ClienteExternoModel> readById(@PathVariable() Integer idCteExt);

    @PostMapping("/clienteExterno")
    public ClienteExternoModel create(@RequestBody ClienteExternoModel clienteExternoModel);

    @PutMapping("/clienteExterno/{idCteExt}")
    public ClienteExternoModel updateById(@PathVariable Integer idCteExt, @RequestBody ClienteExternoModel clienteExternoModel);

    @DeleteMapping("/clienteExterno")
    public String deleteById(@PathParam("idClteExt") Integer idCteExt);

}
