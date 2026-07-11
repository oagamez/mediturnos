import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PacienteService } from '../../core/services/paciente.service';
import { Paciente } from '../../shared/models/paciente';

@Component({
  selector: 'app-pacientes',
  standalone: true,
  imports: [
    CommonModule
  ],
  templateUrl: './pacientes.html',
  styleUrl: './pacientes.css'
})
export class Pacientes implements OnInit {

  private service = inject(PacienteService);

  pacientes: Paciente[] = [];

  ngOnInit(): void {

    this.service.listar().subscribe({

      next: (response) => {

        this.pacientes = response.data;

      },

      error: (error) => {

        console.error(error);

      }

    });

  }

}