package com.pagoya.reclamos.dto;

public record ReclamoUpdateRequest(
        Integer estado,
        String analista,
        String observacion,
        String motivo,
        String nomcliente
) {
}
