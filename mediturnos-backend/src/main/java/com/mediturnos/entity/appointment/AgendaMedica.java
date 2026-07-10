package com.mediturnos.entity.appointment;

import com.mediturnos.enums.DiaSemana;
import com.mediturnos.enums.DuracionConsulta;
import com.mediturnos.entity.common.BaseEntity;
import com.mediturnos.entity.medical.Consultorio;
import com.mediturnos.entity.medical.Medico;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "agendas_medicas")
public class AgendaMedica extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id", nullable = false)
    private Medico medico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consultorio_id", nullable = false)
    private Consultorio consultorio;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DiaSemana diaSemana;

    @Column(nullable = false)
    private LocalTime horaInicio;

    @Column(nullable = false)
    private LocalTime horaFin;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DuracionConsulta duracionConsulta;

}