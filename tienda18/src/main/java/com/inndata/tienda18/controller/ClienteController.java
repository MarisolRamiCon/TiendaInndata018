package com.inndata.tienda18.controller;

import com.inndata.tienda18.model.request.ClienteRequest;
import com.inndata.tienda18.model.response.ClienteResponse;
import com.inndata.tienda18.service.impl.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteService clienteService;

    @GetMapping("/clientes")
    public ResponseEntity<List<ClienteResponse>> readAll() {
        try {
            List<ClienteResponse> clientes = clienteService.readAll();
            return ResponseEntity.ok(clientes);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build(); // 500 Internal Server Error
        }
    }

    @GetMapping("/clientes/{idCliente}")
    public ResponseEntity<ClienteResponse> readById(@PathVariable("idCliente") Integer idCliente) {
        ClienteResponse cliente = clienteService.readById(idCliente);
        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente);
    }

    @PostMapping("/clientes")
    public ResponseEntity<ClienteResponse> create(@RequestBody ClienteRequest request) {
        try {
            ClienteResponse response = clienteService.create(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ClienteResponse());
        }
    }

    @PutMapping("/clientes/{idCliente}")
    public ResponseEntity<ClienteResponse> update(
            @PathVariable Integer idCliente,
            @RequestBody ClienteRequest request) {
        try {
            ClienteResponse response = clienteService.update(idCliente, request);
            if (response == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ClienteResponse());
        }
    }

    @DeleteMapping("/clientes/{idCliente}")
    public ResponseEntity<ClienteResponse> delete(@PathVariable Integer idCliente) {
        try {
            ClienteResponse response = clienteService.deleteById(idCliente);
            if (response == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ClienteResponse());
        }
    }

    @GetMapping("/clientes/porLetra")
    public ResponseEntity<List<ClienteResponse>> findByLetra(@RequestParam String nombre) {
        try {
            List<ClienteResponse> clientes = clienteService.findByLetra(nombre);
            return ResponseEntity.ok(clientes);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity<List<ClienteResponse>> findClienteEliminado() {
        try {
            List<ClienteResponse> clientes = clienteService.findClienteEliminado(false);
            return ResponseEntity.ok(clientes);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/clientes/telefono")
    public ResponseEntity<List<ClienteResponse>> telefonoForaneo(@RequestParam String telefono) {
        try {
            List<ClienteResponse> clientes = clienteService.telefonoForaneo(telefono);
            return ResponseEntity.ok(clientes);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
