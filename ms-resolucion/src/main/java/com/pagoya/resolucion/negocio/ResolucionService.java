package com.pagoya.resolucion.negocio;

import com.pagoya.resolucion.client.ReclamoClient;
import com.pagoya.resolucion.dto.ReclamoDto;
import com.pagoya.resolucion.dto.ResolucionRequest;
import feign.FeignException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class ResolucionService {

    private final ReclamoClient reclamoClient;

    public ResolucionService(ReclamoClient reclamoClient) {
        this.reclamoClient = reclamoClient;
    }

    @CircuitBreaker(name = "ms-reclamos", fallbackMethod = "fallbackResolver")
    public Map<String, Object> resolver(Long num, ResolucionRequest request) {
        ReclamoDto existente;
        try {
            existente = reclamoClient.obtenerPorNumero(num);
        } catch (FeignException.NotFound e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reclamo " + num + " no existe, no se puede resolver");
        }

        if (request.estado() != 2 && request.estado() != 3) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "estado debe ser 2 (procedente) o 3 (improcedente)");
        }

        Map<String, Object> body = Map.of(
                "estado", request.estado(),
                "analista", request.analista(),
                "observacion", request.observacion()
        );

        ReclamoDto actualizado = reclamoClient.actualizar(num, body);

        String etiqueta = actualizado.estado() == 2 ? "PROCEDENTE" : "IMPROCEDENTE";
        Map<String, Object> resp = new LinkedHashMap<>();
        resp.put("mensaje", "Reclamo " + num + " resuelto como " + etiqueta + " correctamente");
        resp.put("numreclamo", actualizado.numreclamo());
        resp.put("estado", actualizado.estado());
        resp.put("estadoDescripcion", etiqueta);
        resp.put("analista", actualizado.analista());
        resp.put("observacion", actualizado.observacion());
        resp.put("reclamo", actualizado);
        resp.put("reclamoPrevio_estado", existente.estado());
        return resp;
    }

    @SuppressWarnings("unused")
    public Map<String, Object> fallbackResolver(Long num, ResolucionRequest request, Throwable e) {
        if (e instanceof ResponseStatusException rse) {
            throw rse;
        }
        Map<String, Object> resp = new LinkedHashMap<>();
        resp.put("mensaje", "Servicio de reclamos no disponible, intente mas tarde");
        resp.put("numreclamo", num);
        resp.put("error", e.getClass().getSimpleName());
        throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Servicio de reclamos no disponible", e);
    }
}
