package com.pagoya.resolucion.client;

import com.pagoya.resolucion.dto.ReclamoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Map;

@FeignClient(name = "ms-reclamos")
public interface ReclamoClient {

    @GetMapping("/reclamos/{num}")
    ReclamoDto obtenerPorNumero(@PathVariable("num") Long num);

    @PutMapping("/reclamos/{num}")
    ReclamoDto actualizar(@PathVariable("num") Long num, @RequestBody Map<String, Object> body);
}
