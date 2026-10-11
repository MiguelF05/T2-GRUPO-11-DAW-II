package com.pagoya.reclamos.repositorio;

import com.pagoya.reclamos.entidad.Reclamo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReclamoRepository extends JpaRepository<Reclamo, Long> {
}
