package com.mediturnos.entity.appointment;

import com.mediturnos.entity.common.BaseEntity;
import com.mediturnos.entity.patient.Paciente;
import com.mediturnos.enums.EstadoCita;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "citas")
public class Cita extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agenda_medica_id", nullable = false)
    private AgendaMedica agendaMedica;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private LocalTime hora;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoCita estado;

    @Column(length = 500)
    private String observaciones;

}