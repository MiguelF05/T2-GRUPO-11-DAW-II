package com.pagoya.reclamos.negocio;

import com.pagoya.reclamos.dto.ReclamoRequest;
import com.pagoya.reclamos.dto.ReclamoUpdateRequest;
import com.pagoya.reclamos.entidad.Reclamo;
import com.pagoya.reclamos.repositorio.ReclamoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDate;
import java.util.List;

@Service
public class ReclamoService {

    private final ReclamoRepository repository;

    public ReclamoService(ReclamoRepository repository) {
        this.repository = repository;
    }

    public Reclamo registrar(ReclamoRequest request) {
        Reclamo r = new Reclamo();
        r.setFechareclamo(request.fechareclamo() != null ? request.fechareclamo() : LocalDate.now());
        r.setNomcliente(request.nomcliente());
        r.setUltimos4tarjeta(request.ultimos4tarjeta());
        r.setMotivo(request.motivo());
        r.setAnalista("No asignado");
        r.setEstado(1);
        r.setObservacion("por evaluar");
        return repository.save(r);
    }

    public List<Reclamo> listar() {
        return repository.findAll();
    }

    public Reclamo buscarPorNumero(Long num) {
        return repository.findById(num)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reclamo " + num + " no existe"));
    }

    public Reclamo actualizar(Long num, ReclamoUpdateRequest request) {
        Reclamo r = buscarPorNumero(num);
        if (request.estado() != null) {
            r.setEstado(request.estado());
        }
        if (request.analista() != null && !request.analista().isBlank()) {
            r.setAnalista(request.analista());
        }
        if (request.observacion() != null && !request.observacion().isBlank()) {
            r.setObservacion(request.observacion());
        }
        if (request.motivo() != null && !request.motivo().isBlank()) {
            r.setMotivo(request.motivo());
        }
        if (request.nomcliente() != null && !request.nomcliente().isBlank()) {
            r.setNomcliente(request.nomcliente());
        }
        return repository.save(r);
    }
}
