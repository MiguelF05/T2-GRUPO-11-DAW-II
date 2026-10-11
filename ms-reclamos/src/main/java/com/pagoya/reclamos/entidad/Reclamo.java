package com.pagoya.reclamos.entidad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "reclamos")
public class Reclamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numreclamo;

    private LocalDate fechareclamo;

    private String nomcliente;

    @Column(length = 4)
    private String ultimos4tarjeta;

    private String motivo;

    private String analista = "No asignado";

    private Integer estado = 1;

    private String observacion = "por evaluar";

    public Reclamo() {
    }

    @PrePersist
    public void prePersist() {
        if (analista == null || analista.isBlank()) {
            analista = "No asignado";
        }
        if (estado == null) {
            estado = 1;
        }
        if (observacion == null || observacion.isBlank()) {
            observacion = "por evaluar";
        }
    }

    public Long getNumreclamo() {
        return numreclamo;
    }

    public void setNumreclamo(Long numreclamo) {
        this.numreclamo = numreclamo;
    }

    public LocalDate getFechareclamo() {
        return fechareclamo;
    }

    public void setFechareclamo(LocalDate fechareclamo) {
        this.fechareclamo = fechareclamo;
    }

    public String getNomcliente() {
        return nomcliente;
    }

    public void setNomcliente(String nomcliente) {
        this.nomcliente = nomcliente;
    }

    public String getUltimos4tarjeta() {
        return ultimos4tarjeta;
    }

    public void setUltimos4tarjeta(String ultimos4tarjeta) {
        this.ultimos4tarjeta = ultimos4tarjeta;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getAnalista() {
        return analista;
    }

    public void setAnalista(String analista) {
        this.analista = analista;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}
