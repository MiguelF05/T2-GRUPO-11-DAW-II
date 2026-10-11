package com.pagoya.resolucion.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ResolucionRequest(
        @NotNull(message = "estado es obligatorio: 2 procedente, 3 improcedente")
        @Min(value = 2, message = "estado debe ser 2 o 3")
        @Max(value = 3, message = "estado debe ser 2 o 3")
        Integer estado,
        @NotBlank(message = "analista es obligatorio")
        String analista,
        @NotBlank(message = "observacion es obligatoria")
        String observacion
) {
}
