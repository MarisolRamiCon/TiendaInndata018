package com.inndata.tienda18.controller;

import com.inndata.tienda18.model.ClienteExternoModel;
import com.inndata.tienda18.service.impl.ClienteExternoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class ClienteExternoControler {
    private final ClienteExternoService clienteExternoService;

    @GetMapping("clienteExterno")
    List<ClienteExternoModel> readAll() {
        return clienteExternoService.readAll();
    }

    @GetMapping("/clienteExterno/{idCteExt}")
    public Optional<ClienteExternoModel> readById(@PathVariable() Integer idCteExt) {
        return clienteExternoService.readById(idCteExt);
    }

    @PostMapping("/clienteExterno")
    public ClienteExternoModel create(@RequestBody ClienteExternoModel clienteExternoModel) {
        return clienteExternoService.create(clienteExternoModel);
    }

    @PutMapping("/clienteExterno/{idCteExt}")
    public ClienteExternoModel updateById(@PathVariable Integer idCteExt, @RequestBody ClienteExternoModel clienteExternoModel) {
        return clienteExternoService.updateById(idCteExt, clienteExternoModel);
    }

    @DeleteMapping("/clienteExterno")
    public String deleteById(Integer idCteExt) {
        return clienteExternoService.deleteById(idCteExt);
    }
}
