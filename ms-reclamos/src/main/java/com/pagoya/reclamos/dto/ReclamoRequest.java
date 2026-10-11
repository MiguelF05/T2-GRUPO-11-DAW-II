package com.pagoya.reclamos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record ReclamoRequest(
        LocalDate fechareclamo,
        @NotBlank(message = "nomcliente es obligatorio") String nomcliente,
        @NotBlank(message = "ultimos4tarjeta es obligatorio")
        @Size(min = 4, max = 4, message = "ultimos4tarjeta debe tener 4 digitos")
        @Pattern(regexp = "\\d{4}", message = "ultimos4tarjeta debe ser numerico de 4 digitos")
        String ultimos4tarjeta,
        @NotBlank(message = "motivo es obligatorio") String motivo
) {
}
