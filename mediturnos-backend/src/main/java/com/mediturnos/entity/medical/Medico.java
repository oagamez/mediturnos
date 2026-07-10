package com.mediturnos.entity.medical;

import com.mediturnos.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "medicos")
public class Medico extends BaseEntity {

    @Column(nullable = false, unique = true, length = 20)
    private String documento;

    @Column(nullable = false, length = 80)
    private String nombres;

    @Column(nullable = false, length = 80)
    private String apellidos;

    @Column(nullable = false, unique = true, length = 30)
    private String registroProfesional;

    @Column(nullable = false, length = 20)
    private String telefono;

    @Column(nullable = false, unique = true, length = 120)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "especialidad_id", nullable = false)
    private Especialidad especialidad;

}