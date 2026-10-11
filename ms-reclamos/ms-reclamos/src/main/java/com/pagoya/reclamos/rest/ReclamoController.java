package com.pagoya.reclamos.rest;

import com.pagoya.reclamos.dto.ReclamoRequest;
import com.pagoya.reclamos.dto.ReclamoUpdateRequest;
import com.pagoya.reclamos.entidad.Reclamo;
import com.pagoya.reclamos.negocio.ReclamoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/reclamos")
public class ReclamoController {

    private final ReclamoService service;

    public ReclamoController(ReclamoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Reclamo registrar(@Valid @RequestBody ReclamoRequest request) {
        return service.registrar(request);
    }

    @GetMapping
    public List<Reclamo> listar() {
        return service.listar();
    }

    @GetMapping("/{num}")
    public Reclamo consultarPorNumero(@PathVariable Long num) {
        return service.buscarPorNumero(num);
    }

    @PutMapping("/{num}")
    public Reclamo actualizar(@PathVariable Long num, @RequestBody ReclamoUpdateRequest request) {
        return service.actualizar(num, request);
    }
}
