package com.pagoya.resolucion.rest;

import com.pagoya.resolucion.dto.ResolucionRequest;
import com.pagoya.resolucion.negocio.ResolucionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/resoluciones")
public class ResolucionController {

    private final ResolucionService service;

    public ResolucionController(ResolucionService service) {
        this.service = service;
    }

    @PutMapping("/{num}")
    public Map<String, Object> resolver(@PathVariable Long num, @Valid @RequestBody ResolucionRequest request) {
        return service.resolver(num, request);
    }
}
