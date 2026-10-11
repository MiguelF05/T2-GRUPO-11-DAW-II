package com.pagoya.resolucion.dto;

import java.time.LocalDate;

public record ReclamoDto(
        Long numreclamo,
        LocalDate fechareclamo,
        String nomcliente,
        String ultimos4tarjeta,
        String motivo,
        String analista,
        Integer estado,
        String observacion
) {
}
